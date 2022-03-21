package com.rs.businessaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BusinessaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessaccountApplication.class, args);
	}

}
