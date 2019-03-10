package com.zero.user.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.api.model.user.AppUser;
import com.zero.api.model.user.UserCredential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserCredentialsDao extends BaseMapper<UserCredential>{

	@Insert("insert into user_credentials(username, type, user_id) values(#{username}, #{type}, #{userId})")
	int save(UserCredential userCredential);

	@Select("select * from user_credentials t where t.username = #{username}")
	UserCredential findByUsername(String username);

	@Select("select u.* from app_user u inner join user_credentials c on c.user_id = u.id where c.username = #{username}")
	AppUser findUserByUsername(String username);

	@Delete("delete from user_credentials where user_id = #{userid}")
	int deleteByUserId(Long userid);
}
