package com.si.sipp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;

//@Profile("production")
//@EnableAutoConfiguration
//@PropertySource("classpath:/app-config.properties")
@SpringBootApplication
public class SIPPApplication {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SIPPApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SIPPApplication.class, args);
    }

}
