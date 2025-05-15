package com.example.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("client-service", r -> r.path("/clients/**")
                .uri("lb://CLIENT-SERVICE"))
            .route("employee-service", r -> r.path("/employees/**")
                .uri("lb://EMPLOYEE-SERVICE"))
            .route("holiday-service", r -> r.path("/holidays/**")
                .uri("lb://HOLIDAY-SERVICE"))
            .route("hr-service", r -> r.path("/hr/**")
                .uri("lb://HR-SERVICE"))
            .route("auth-service", r -> r.path("/auth/**")
                .uri("lb://AUTHENTICATION-SERVICE"))
            .build();
    }

    
}
