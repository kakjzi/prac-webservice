package com.kakjzi.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@RequiredArgsConstructor
public enum Role {
    Guest("ROLE_GUEST", "손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;
}
