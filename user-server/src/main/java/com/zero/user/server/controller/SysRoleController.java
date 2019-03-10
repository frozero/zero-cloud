package com.zero.user.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.model.base.BaseController;
import com.zero.api.model.common.Result;
import com.zero.api.model.log.LogAnnotation;
import com.zero.api.model.user.SysRole;
import com.zero.user.server.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SysRoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 管理后台添加角色
	 * 
	 * @param sysRole
	 */
	@PreAuthorize("hasAuthority('system:role:save')")
	@LogAnnotation(module = "添加角色")
	@PostMapping("/roles")
	public Result save(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getCode())) {
			throw new IllegalArgumentException("角色code不能为空");
		}
		if (StringUtils.isBlank(sysRole.getName())) {
			sysRole.setName(sysRole.getCode());
		}
		return result(sysRoleService.save(sysRole));
	}

	/**
	 * 管理后台删除角色
	 * 
	 * @param id
	 */
	@PreAuthorize("hasAuthority('system:role:delete')")
	@LogAnnotation(module = "删除角色")
	@DeleteMapping("/roles/{id}")
	public void deleteRole(@PathVariable Long id) {
		sysRoleService.deleteRole(id);
	}

	/**
	 * 管理后台修改角色
	 * 
	 * @param sysRole
	 */
	@LogAnnotation(module = "修改角色")
	@PreAuthorize("hasAuthority('system:role:update')")
	@PutMapping("/roles")
	public Result update(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getName())) {
			throw new IllegalArgumentException("角色名不能为空");
		}

		return result(sysRoleService.update(sysRole));
	}

	/**
	 * 管理后台给角色分配权限
	 *
	 * @param id            角色id
	 * @param permissionIds 权限ids
	 */
	@LogAnnotation(module = "分配权限")
	@PreAuthorize("hasAuthority('system:role:save')")
	@PostMapping("/roles/{id}/permissions")
	public Result setPermissionToRole(@PathVariable Long id, @RequestBody Set<Long> permissionIds) {
		return result(sysRoleService.setPermissionToRole(id, permissionIds));
	}

	/**
	 * 获取角色的权限
	 * 
	 * @param id
	 */
	@PreAuthorize("hasAuthority('system:role')")
	@GetMapping("/roles/{id}/permissions")
	public Result findPermissionsByRoleId(@PathVariable Long id) {
		return result(sysRoleService.findPermissionsByRoleId(id));
	}

	@PreAuthorize("hasAuthority('system:role')")
	@GetMapping("/roles/{id}")
	public SysRole findById(@PathVariable Long id) {
		return sysRoleService.findById(id);
	}

	/**
	 * 搜索角色
	 * 
	 */
	@PreAuthorize("hasAuthority('system:role')")
	@GetMapping("/roles")
	public Page<SysRole> findRoles(Page<SysRole> page, SysRole sysRole) {
		return sysRoleService.findRoles(page, sysRole);
	}

}
