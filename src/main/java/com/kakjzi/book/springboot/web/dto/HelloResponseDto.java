package com.kakjzi.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언된 'final' 필드 생성자를 생성!
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
