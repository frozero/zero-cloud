package com.zero.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.log.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogDao extends BaseMapper<Log> {

	@Insert("insert into t_log(username, module, params, remark, flag, create_time) values(#{username}, #{module}, #{params}, #{remark}, #{flag}, #{createTime})")
	int save(Log log);

	int count(Map<String, Object> params);

	List<Log> findData(Page page, @Param("log") Map<String, Object> params);
}
