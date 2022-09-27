package com.igibgo.igibgo.config;

import com.igibgo.igibgo.interceptor.BackstageLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    private BackstageLoginInterceptor loginInterceptor;

//     add login interceptor (check login for backstage)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/backstage/**")
                .excludePathPatterns("/backstage/login")
                .excludePathPatterns("/backstage/logout")
                .excludePathPatterns("/redis/**");
    }
}
