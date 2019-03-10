package com.cloud.gateway.feign;

import com.zero.api.model.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("log-server")
public interface LogClient {

	@PostMapping("/logs-anon/internal")
	void save(@RequestBody Log log);
}
