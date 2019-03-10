package com.zero.user.server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("notification-server")
public interface SmsClient {

	@GetMapping(value = "/notification-anon/internal/phone", params = { "key", "code" })
	public String matcheCodeAndGetPhone(@RequestParam("key") String key, @RequestParam("code") String code,
                                        @RequestParam(value = "delete", required = false) Boolean delete,
                                        @RequestParam(value = "second", required = false) Integer second);
}
