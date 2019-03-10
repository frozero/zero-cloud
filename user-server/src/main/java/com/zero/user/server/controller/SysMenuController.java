package com.zero.user.server.controller;

import com.zero.api.model.base.BaseController;
import com.zero.api.model.common.Result;
import com.zero.user.server.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单
     *
     * @param
     */
    @PreAuthorize("hasAuthority('system')")
    @GetMapping("/menus")
    public Result menus() {
        return result(sysMenuService.listAll());
    }

    @GetMapping("/refreshCache-anon")
    public void refreshCache() {
        sysMenuService.refreshCache();
    }
}
