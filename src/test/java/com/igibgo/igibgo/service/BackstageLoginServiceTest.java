package com.igibgo.igibgo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BackstageLoginServiceTest {

    @Test
    void loginByEmail() {
        BackstageLoginService service=new BackstageLoginService();
        service.loginByEmail("brian.zu.2026@huilieducation.cn","ThisisGreat00");
    }
}