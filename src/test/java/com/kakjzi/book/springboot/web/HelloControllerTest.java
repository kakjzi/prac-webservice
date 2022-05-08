package com.kakjzi.book.springboot.web;

import org.junit.runner.RunWith;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static  org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void HELLO가_리턴된다() throws Exception{
        String HELLO = "HELLO";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(HELLO));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "jiwoo";
        int amount = 1000;

        mvc.perform(
                // 요청
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                //검증
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }


}