package com.zero.user.server.controller;

import com.zero.api.model.common.Page;
import com.zero.api.model.log.LogAnnotation;
import com.zero.api.model.user.SysPermission;
import com.zero.user.server.service.SysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 管理后台添加权限
	 * 
	 * @param sysPermission
	 * @return
	 */
	@LogAnnotation(module = "添加权限")
	@PostMapping("/permissions")
	public SysPermission save(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getPermission())) {
			throw new IllegalArgumentException("权限标识不能为空");
		}
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.save(sysPermission);

		return sysPermission;
	}

	/**
	 * 管理后台修改权限
	 * 
	 * @param sysPermission
	 */
	@LogAnnotation(module = "修改权限")
	@PutMapping("/permissions")
	public SysPermission update(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.update(sysPermission);

		return sysPermission;
	}

	/**
	 * 删除权限标识
	 * 
	 * @param id
	 */
	@LogAnnotation(module = "删除权限")
	@DeleteMapping("/permissions/{id}")
	public void delete(@PathVariable Long id) {
		sysPermissionService.delete(id);
	}

	/**
	 * 查询所有的权限标识
	 */
	@GetMapping("/permissions")
	public Page<SysPermission> findPermissions(@RequestParam Map<String, Object> params) {
		return sysPermissionService.findPermissions(params);
	}
}
