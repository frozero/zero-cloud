package com.zero.user.server.dao;

import com.zero.api.model.user.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 用户角色关系<br>
 * 用户和角色是多对多关系，sys_role_user是中间表
 *
 * @author zero
 */
@Mapper
public interface UserRoleDao {

	int deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

	@Insert("insert into sys_role_user(user_id, role_id) values(#{userId}, #{roleId})")
	int saveUserRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

	/**
	 * 根据用户id获取角色
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select r.* from sys_role_user ru inner join sys_role r on r.id = ru.role_id where ru.user_id = #{userId}")
	Set<SysRole> findRolesByUserId(Long userId);

}
