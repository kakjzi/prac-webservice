package com.kakjzi.book.springboot.config.auth;

import com.kakjzi.book.springboot.domain.user.User;
import com.kakjzi.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Login Service Code
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();

        /**
         * Login Primary KEY
         * Ex) Google = 'sub'
         */
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        //User Attributes
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpate(attributes);

        //User DTO
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                collections.singleton(
                        new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpate(OAuthAttributes attributes){
        User user   = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
