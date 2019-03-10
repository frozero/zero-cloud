package com.zero.user.server.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.common.ResultCode;
import com.zero.api.model.user.SysPermission;
import com.zero.api.model.user.SysRole;

import java.util.Set;

public interface SysRoleService {

	ResultCode save(SysRole sysRole);

	ResultCode update(SysRole sysRole);

	void deleteRole(Long id);

	ResultCode setPermissionToRole(Long id, Set<Long> permissionIds);

	SysRole findById(Long id);

	Page<SysRole> findRoles(Page<SysRole> page, SysRole sysRole);

	Set<SysPermission> findPermissionsByRoleId(Long roleId);
}
