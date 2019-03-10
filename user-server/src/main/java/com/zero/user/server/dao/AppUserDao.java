package com.zero.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.user.AppUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppUserDao extends BaseMapper<AppUser> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into app_user(username, password, nickname, head_img_url, phone, sex, enabled, type, create_time, update_time) "
			+ "values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
	Long save(AppUser appUser);

	int update(AppUser appUser);

	@Select("select * from app_user t where t.id = #{id}")
	AppUser findById(Long id);

	List<AppUser> list(Page page, @Param("query") AppUser appUser);

}
