package com.zero.user.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("gateway-zuul")
public interface GatewayClient {

	@GetMapping(value = "/sys/login-wechat", params = { "openid", "tempCode" })
	Map<String, Object> loginByWechat(@RequestParam("openid") String openid, @RequestParam("tempCode") String tempCode);
}
