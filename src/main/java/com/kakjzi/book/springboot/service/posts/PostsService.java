package com.kakjzi.book.springboot.service.posts;

import com.kakjzi.book.springboot.domain.posts.Posts;
import com.kakjzi.book.springboot.domain.posts.PostsRepository;
import com.kakjzi.book.springboot.web.dto.PostsResponseDto;
import com.kakjzi.book.springboot.web.dto.PostsSaveRequestDto;
import com.kakjzi.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}

/**
 * update 기능에서 DB에 쿼리를 날리는 부분이 없습니다.
 * -> JPA 영속성 컨텍스트 때문 (영속성 컨텍스트란? 엔티티를 영구 저장하는 환경을 말함)
 *
 * JPA 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 DB에서 데이터를 가져오면 이 데이터는 '영속성 컨텍스트'가 유지된 상태입니다.
 * 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영합니다.
 * -> Entity 값만 변경하면 별도로 update 쿼리를 날릴 필요없이 '더티체킹'을 해줌.
 */