package com.zero.oauth.server.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启session共享
 * 
 * @author zero
 *
 */
@EnableRedisHttpSession
public class SessionConfig {

}
