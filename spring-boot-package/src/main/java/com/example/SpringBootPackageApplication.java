package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/***
 * @see
 */
@SpringBootApplication
public class SpringBootPackageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // return super.configure(builder);
        return builder.sources(SpringBootPackageApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPackageApplication.class, args);
    }

}

