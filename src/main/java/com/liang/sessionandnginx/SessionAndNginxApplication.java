package com.liang.sessionandnginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

@EnableRedisHttpSession
@MapperScan(basePackages = {"com.liang.sessionandnginx.mapper"})
@SpringBootApplication
public class SessionAndNginxApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SessionAndNginxApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SessionAndNginxApplication.class);
    }
}
