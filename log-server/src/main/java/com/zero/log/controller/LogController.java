package com.zero.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.base.BaseController;
import com.zero.api.model.common.Result;
import com.zero.api.model.log.Log;
import com.zero.api.model.log.constants.LogModule;
import com.zero.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	@PostMapping("/logs-anon/internal")
	public void save(@RequestBody Log log) {
		logService.save(log);
	}

	/**
	 * 日志模块<br>
	 * 2018.07.29作废
	 */
	@Deprecated
	@PreAuthorize("hasAuthority('log:query')")
	@GetMapping("/logs-modules")
	public Map<String, String> logModule() {
		return LogModule.MODULES;
	}

	/**
	 * 日志查询
	 * 
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('system:monitor:log')")
	@GetMapping("/logs")
	public Result findLogs(Page<Log> page, @RequestParam Map<String, Object> params) {
		return result(logService.findLogs(page, params));
	}

}
