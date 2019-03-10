package com.zero.user.server.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	@LoadBalanced
	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
		return factory.getUserInfoRestTemplate();
	}
	@Bean
	public RestTemplate restTemplate() {
		PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager();
		pollingConnectionManager.setMaxTotal(200);
		pollingConnectionManager.setDefaultMaxPerRoute(200);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(pollingConnectionManager);
//		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
		HttpClient httpClient = httpClientBuilder.build();

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		// 超时时间
		clientHttpRequestFactory.setConnectTimeout(5000);
		clientHttpRequestFactory.setReadTimeout(5000);
		clientHttpRequestFactory.setConnectionRequestTimeout(5000);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(clientHttpRequestFactory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		return restTemplate;
	}
}
