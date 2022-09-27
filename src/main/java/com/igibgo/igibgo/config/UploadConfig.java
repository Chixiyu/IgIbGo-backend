package com.igibgo.igibgo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory= new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofGigabytes(20));
        return factory.createMultipartConfig();
    }
}
