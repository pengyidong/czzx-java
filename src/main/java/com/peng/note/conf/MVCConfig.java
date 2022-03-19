package com.peng.note.conf;

import com.peng.note.aop.LoginHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author : code
 * @Date 2022/2/20 22:02
 * @Version 1.0
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LoginHandler loginHandler() {
        return new LoginHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandler()).excludePathPatterns("/static/*")
                .excludePathPatterns("/error").addPathPatterns("/**");
    }

}