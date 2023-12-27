package com.texo.apitest.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootTest
class MoviesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void MoviesController_Get_ReturnSuccess() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/movies/awards/interval");
        MvcResult result = mockMvc
                .perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String fileCompare = this.getFileToCompare();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assertions.assertEquals(fileCompare, result.getResponse().getContentAsString());
    }

    private String getFileToCompare() {
        StringBuilder sb = new StringBuilder();
        Path path = Path.of(Objects.requireNonNull(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("expected_result.json")).getPath()));
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
