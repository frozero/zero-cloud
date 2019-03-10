package com.zero.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.zero.api.model.common.ResultCode;
import com.zero.api.model.user.SysPermission;
import com.zero.api.model.user.SysRole;
import com.zero.api.model.user.constants.UserCenterMq;
import com.zero.user.server.dao.RolePermissionDao;
import com.zero.user.server.dao.SysRoleDao;
import com.zero.user.server.dao.UserRoleDao;
import com.zero.user.server.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Transactional
    @Override
    public ResultCode save(SysRole sysRole) {
        int count = sysRoleDao.selectCount(new QueryWrapper<SysRole>().eq("name", sysRole.getName()));
        if (count > 0) {
            throw new IllegalArgumentException("角色名称已存在");
        }
        count = sysRoleDao.selectCount(new QueryWrapper<SysRole>().eq("code", sysRole.getCode()));
        if (count > 0) {
            throw new IllegalArgumentException("角色编码已存在");
        }
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());

        log.info("保存角色：{}", sysRole);
        return sysRoleDao.insert(sysRole) > 0 ? ResultCode.SUCCESS : ResultCode.FAILURE;
    }

    @Transactional
    @Override
    public ResultCode update(SysRole sysRole) {
        int count = sysRoleDao.selectCount(new QueryWrapper<SysRole>()
                .eq("name", sysRole.getName())
                .ne("id", sysRole.getId()));
        if (count > 0) {
            throw new IllegalArgumentException("角色名称已存在");
        }
        count = sysRoleDao.selectCount(new QueryWrapper<SysRole>()
                .eq("code", sysRole.getCode())
                .ne("id", sysRole.getId()));
        if (count > 0) {
            throw new IllegalArgumentException("角色编码已存在");
        }
        sysRole.setUpdateTime(new Date());

        log.info("修改角色：{}", sysRole);
        return sysRoleDao.updateById(sysRole) > 0 ? ResultCode.SUCCESS : ResultCode.FAILURE;
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        SysRole sysRole = sysRoleDao.findById(id);

        sysRoleDao.deleteById(id);
        rolePermissionDao.deleteRolePermission(id, null);
        userRoleDao.deleteUserRole(null, id);

        log.info("删除角色：{}", sysRole);

        // 发布role删除的消息
        amqpTemplate.convertAndSend(UserCenterMq.MQ_EXCHANGE_USER, UserCenterMq.ROUTING_KEY_ROLE_DELETE, id);
    }

    /**
     * 给角色设置权限
     *
     * @param roleId
     * @param permissionIds
     */
    @Transactional
    @Override
    public ResultCode setPermissionToRole(Long roleId, Set<Long> permissionIds) {
        SysRole sysRole = sysRoleDao.findById(roleId);
        if (sysRole == null) {
            throw new IllegalArgumentException("角色不存在");
        }

        // 查出角色对应的old权限
        Set<Long> oldPermissionIds = rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId)).stream()
                .map(SysPermission::getId).collect(Collectors.toSet());

        // 需要添加的权限
        Collection<Long> addPermissionIds = org.apache.commons.collections4.CollectionUtils.subtract(permissionIds,
                oldPermissionIds);
        if (!CollectionUtils.isEmpty(addPermissionIds)) {
            addPermissionIds.forEach(permissionId -> {
                if (permissionId != null) {
                    rolePermissionDao.saveRolePermission(roleId, permissionId);
                }

            });
        }
        // 需要移除的权限
        Collection<Long> deletePermissionIds = org.apache.commons.collections4.CollectionUtils
                .subtract(oldPermissionIds, permissionIds);
        if (!CollectionUtils.isEmpty(deletePermissionIds)) {
            deletePermissionIds.forEach(permissionId -> rolePermissionDao.deleteRolePermission(roleId, permissionId));
        }

        log.info("给角色id：{}，分配权限：{}", roleId, permissionIds);
        return ResultCode.SUCCESS;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleDao.findById(id);
    }

    @Override
    public Page<SysRole> findRoles(Page<SysRole> page, SysRole sysRole) {
        page.setRecords(sysRoleDao.list(page, sysRole));
        return page;
    }

    @Override
    public Set<SysPermission> findPermissionsByRoleId(Long roleId) {
        return rolePermissionDao.findPermissionsByRoleIds(Sets.newHashSet(roleId));
    }
}
