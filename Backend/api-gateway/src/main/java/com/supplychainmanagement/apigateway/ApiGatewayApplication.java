package com.supplychainmanagement.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@Configuration
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
		//dynamic routing
		return builder.routes()
				.route(r->r.path("/supplier/**").uri("lb://SUPPLIER-SERVICE"))
				.route(r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
				.route(r->r.path("/inventory/**").uri("lb://INVENTORY-SERVICE"))
				.route(r->r.path("/retailer/**").uri("lb://RETAILER-SERVICE"))
				.build();
	}

}
