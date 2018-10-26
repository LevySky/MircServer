package com.mirc.serverconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableConfigServer
@SpringBootApplication
public class ServerConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerConfigApplication.class, args);
	}


}
