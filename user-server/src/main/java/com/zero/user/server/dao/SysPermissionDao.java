package com.zero.user.server.dao;

import com.zero.api.model.user.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysPermissionDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_permission(permission, name, create_time, update_time) values(#{permission}, #{name}, #{createTime}, #{createTime})")
	int save(SysPermission sysPermission);

	@Update("update sys_permission t set t.name = #{name}, t.permission = #{permission}, t.update_time = #{updateTime} where t.id = #{id}")
	int update(SysPermission sysPermission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(Long id);

	@Select("select * from sys_permission t where t.id = #{id}")
	SysPermission findById(Long id);

	@Select("select * from sys_permission t where t.permission = #{permission}")
	SysPermission findByPermission(String permission);

	int count(Map<String, Object> params);

	List<SysPermission> findData(Map<String, Object> params);

	@Select("select t.id from sys_permission t where t.type=0 and t.permission like concat('%', #{permission})")
	Long findIdByPermission(String permission);

}
