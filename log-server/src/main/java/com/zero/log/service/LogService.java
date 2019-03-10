package com.zero.log.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.log.Log;

import java.util.Map;

public interface LogService {

	/**
	 * 保存日志
	 *
	 * @param log
	 */
	void save(Log log);

	Page<Log> findLogs(Page<Log> page, Map<String, Object> params);

}
