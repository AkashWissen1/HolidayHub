package com.example.demo;
 
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.gateway.route.RouteLocator;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
 
@SpringBootApplication

@EnableDiscoveryClient  // Enables Eureka Discovery

public class ApiGatewayApplication {
 
    public static void main(String[] args) {

        SpringApplication.run(ApiGatewayApplication.class, args);

    }
 
    @Bean

    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()

        .route(r->r.path("/clients/**").and().uri("http://localhost:8081"))
        .route(r->r.path("/employees/**").and().uri("http://localhost:8085"))
        .route(r->r.path("/holidays/**").and().uri("http://localhost:8082"))
        .route(r->r.path("/hr/**").and().uri("http://localhost:8084"))
        .route(r->r.path("/auth/**").and().uri("http://localhost:8083"))


        .build();

    }

}

 