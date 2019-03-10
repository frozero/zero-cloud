package com.zero.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.user.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(code, name, create_time, update_time) values(#{code}, #{name}, #{createTime}, #{createTime})")
	int save(SysRole sysRole);

	@Select("select * from sys_role t where t.id = #{id}")
	SysRole findById(Long id);

	@Select("select * from sys_role t where t.code = #{code}")
	SysRole findByCode(String code);

	List<SysRole> list(Page page, @Param("query") SysRole sysRole);

}
