package com.zero.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证中心
 * 
 * @author zero
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthServerApplication.class, args);
	}

}