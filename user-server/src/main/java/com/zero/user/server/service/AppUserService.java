package com.zero.user.server.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.common.ResultCode;
import com.zero.api.model.user.AppUser;
import com.zero.api.model.user.LoginAppUser;
import com.zero.api.model.user.SysRole;

import java.util.Set;

public interface AppUserService {

	ResultCode addAppUser(AppUser appUser);

	ResultCode updateAppUser(AppUser appUser);

	ResultCode deleteAppUser(Long id);

	LoginAppUser findByUsername(String username);

	AppUser findById(Long id);

	void setRoleToUser(Long id, Set<Long> roleIds);

	void updatePassword(Long id, String oldPassword, String newPassword);

//	Page<AppUser> findUsers(Map<String, Object> params);

	Set<SysRole> findRolesByUserId(Long userId);

	void bindingPhone(Long userId, String phone);

	IPage<AppUser> listPage(Page<AppUser> page, AppUser user);

	ResultCode updateEnabled(AppUser user);
}
