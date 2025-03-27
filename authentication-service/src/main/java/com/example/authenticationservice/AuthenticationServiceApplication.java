package com.example.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
<<<<<<< HEAD:authentication-service/src/main/java/com/example/authenticationservice/AuthenticationServiceApplication.java
public class AuthenticationServiceApplication {
=======
@EnableDiscoveryClient
public class EmployeeApplication {
>>>>>>> f391b572e06f27c43a1c6affa4bd819c07f14a9d:employee-service/src/main/java/com/example/demo/EmployeeApplication.java

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
