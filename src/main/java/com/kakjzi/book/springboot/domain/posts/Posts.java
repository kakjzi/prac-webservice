package com.kakjzi.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
/**
 * 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 같다.
 * 생성자의 경우 지금 채워야 할  필드가 무엇인지 명확히 지정할 수 없고
 * 빌더는 어느 필드에 어떤 값을 채워야할지 명확하게 인지할 수 있음.
 */
