package com.dealsfinder.deal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DealServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealServiceApplication.class, args);
	}
}
