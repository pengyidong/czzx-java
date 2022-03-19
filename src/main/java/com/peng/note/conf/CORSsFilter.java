package com.peng.note.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.filter.CorsFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author : code
 * @Date 2022/3/7 21:37
 * @Version 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CORSsFilter extends WebMvcConfigurerAdapter implements Filter {


    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }


    private CorsConfiguration corsConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURI();
        String method = request.getMethod();
        log.info("--->>>>请求类型{}，url:{}",method,url);
        HashSet allowedOrigins = new HashSet(Arrays.asList("*"));
        String originHeader = request.getHeader("Origin");
        HttpServletResponse response = (HttpServletResponse) res;

        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }else {
            response.setHeader("Access-Control-Allow-Origin","*" );
        }
        response.setHeader("Allow", "*");
        // HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Request-Headers", "authorization,locale");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Authorization,Accept-Language,Accept-Encoding," +
                "ContentType,Content-Type, Accept, X-Requested-With,ContentType,authorization,locale");
        if ("IE".equals(req.getParameter("type"))) {
            ((HttpServletResponse) response).setHeader(
                    "XDomainRequestAllowed", "1");
        }
        if (request.getMethod().toLowerCase().equals("options")) {
            response.setHeader("Content-type", "text/html");
            res.getWriter().write("options OK");
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(req, res);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .maxAge(3600);
    }


}
