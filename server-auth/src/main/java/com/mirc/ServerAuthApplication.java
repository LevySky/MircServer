package com.mirc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServerAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServerAuthApplication.class, args);
	}
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
