package com.zero.user.server.config;

import com.zero.api.model.user.WechatInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    private String domain;
    private Map<String, WechatInfo> infos;

}
