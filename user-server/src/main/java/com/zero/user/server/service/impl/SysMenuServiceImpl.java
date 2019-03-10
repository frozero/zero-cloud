package com.zero.user.server.service.impl;

import com.zero.api.common.utils.JsonUtil;
import com.zero.api.model.user.SysMenu;
import com.zero.api.model.user.constants.UserCacheConstant;
import com.zero.user.server.dao.SysMenuDao;
import com.zero.user.server.dao.SysPermissionDao;
import com.zero.user.server.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService, InitializingBean {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void afterPropertiesSet() {
        cacheMenus();
    }

    private List<SysMenu> cacheMenus() {
        List<SysMenu> list = sysMenuDao.listAll();
        list.forEach(m -> {
            if (m.getIsLeaf() == 1) {
                m.setChildren(sysMenuDao.findPermissionsByMenuId(m.getId()));
            }
            m.setPermissionId(sysPermissionDao.findIdByPermission(m.getCode()));
            m.setPermission(m.getCode());
        });
        List<SysMenu> firstLevelMenus = list.stream().filter(m -> m.getParentId() == 0)
                .sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        firstLevelMenus.forEach(m -> setChild(m, list));
        stringRedisTemplate.opsForValue().set(UserCacheConstant.KEY_MENUS_ALL, JsonUtil.objectToString(firstLevelMenus), 30, TimeUnit.DAYS);
        log.info("菜单更新：{}", JsonUtil.objectToString(firstLevelMenus));
        return firstLevelMenus;
    }

    private void setChild(SysMenu menu, List<SysMenu> menus) {
        List<SysMenu> child = menus.stream().filter(m -> m.getParentId().equals(menu.getId()))
                .sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(child)) {
            menu.setChildren(child);
            child.forEach(m -> setChild(m, menus));
        }
    }

    @Override
    public List<SysMenu> listAll() {
        if(!stringRedisTemplate.hasKey(UserCacheConstant.KEY_MENUS_ALL)){
            cacheMenus();
        }
        List<SysMenu> list = JsonUtil.jsonToList(stringRedisTemplate.opsForValue().get(UserCacheConstant.KEY_MENUS_ALL), SysMenu[].class);
        if (CollectionUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public void refreshCache() {
        cacheMenus();
    }

}
