package com.zelda.ZeldaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ZeldaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeldaApiApplication.class, args);
	}

}
