package com.igibgo.igibgo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class IgIbGoApplicationTests {

    @Test
    void contextLoads() {
        String token= UUID.randomUUID().toString();
        System.out.println(token);
    }

}
