package com.zero.log.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.log.Log;
import com.zero.log.dao.LogDao;
import com.zero.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 日志存储到mysql实现
 *
 * @author zero
 */
//@Primary
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	/**
	 * 将日志保存到数据库<br>
	 * 注解@Async是开启异步执行
	 *
	 * @param log
	 */
	@Async
	@Override
	public void save(Log log) {
		if (log.getCreateTime() == null) {
			log.setCreateTime(new Date());
		}
		if (log.getFlag() == null) {
			log.setFlag(Boolean.TRUE);
		}

		logDao.save(log);
	}

	@Override
	public Page<Log> findLogs(Page<Log> page, Map<String, Object> params) {
		page.setRecords(logDao.findData(page, params));
		return page;
	}
}
