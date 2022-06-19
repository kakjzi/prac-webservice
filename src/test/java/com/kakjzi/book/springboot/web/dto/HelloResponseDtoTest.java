package com.kakjzi.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        // 롬복으로 받아줌
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // dto 값과 비교 테스트
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

    /**
     * 참고
     * Junit vs assertj
     * https://www.youtube.com/watch?v=zLx_fI24UXM&t=408s
     */