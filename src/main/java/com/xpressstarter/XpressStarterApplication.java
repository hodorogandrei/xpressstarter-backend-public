package com.xpressstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;

@SpringBootApplication
@EnableEntityLinks
public class XpressStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(XpressStarterApplication.class, args);
	}
}
