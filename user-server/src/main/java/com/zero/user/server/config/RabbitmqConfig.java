package com.zero.user.server.config;

import com.zero.api.model.user.constants.UserCenterMq;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 * 
 * @author zero
 *
 */
@Configuration
public class RabbitmqConfig {

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(UserCenterMq.MQ_EXCHANGE_USER);
	}
}
