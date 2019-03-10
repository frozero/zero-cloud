package com.zero.user.server.dao;

import com.zero.api.model.user.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysMenuDao {

	@Select("select * from sys_menu")
	List<SysMenu> listAll();

	List<SysMenu> findPermissionsByMenuId(@Param("menuId") Long menuId);
}
