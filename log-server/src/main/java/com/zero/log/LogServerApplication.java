package com.zero.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 日志中心
 * 
 * @author zero
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogServerApplication.class, args);
	}

}