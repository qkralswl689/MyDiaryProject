package com.goodee.diary.demo.diary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 절대경로 지정
        registry.addResourceHandler("/diaryimg/**")
                .addResourceLocations("file:///C:\\Users\\82104\\Desktop\\MyDiaryProject\\diaryimg/");
        
        // 상대경로지정
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");


    }

}
