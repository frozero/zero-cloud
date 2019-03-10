package com.zero.user.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.api.common.utils.AppUserUtil;
import com.zero.api.model.base.BaseController;
import com.zero.api.model.common.Result;
import com.zero.api.model.log.LogAnnotation;
import com.zero.api.model.user.AppUser;
import com.zero.api.model.user.LoginAppUser;
import com.zero.user.server.feign.SmsClient;
import com.zero.user.server.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
public class UserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    /**
     * 当前登录用户 LoginAppUser
     */
    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/users/current")
    public Result getLoginAppUser() {
        return result(AppUserUtil.getLoginAppUser());
    }

    @GetMapping(value = "/users-anon/internal", params = "username")
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
    }

    /**
     * 用户查询
     *
     * @param
     */
    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/users")
    public Result findUsers(Page<AppUser> page, AppUser user) {
        return result(appUserService.listPage(page, user));
    }

//    /**
//     * 用户查询
//     *
//     * @param params
//     */
//    @PreAuthorize("hasAuthority('back:user:query')")
//    @GetMapping("/users2")
//    public Page<AppUser> findUsers2(@RequestParam Map<String, Object> params) {
//        return appUserService.findUsers(params);
//    }

    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/users/{id}")
    public Result findUserById(@PathVariable Long id) {
        return result(appUserService.findById(id));
    }

    /**
     * 添加用户,根据用户名注册
     *
     * @param appUser
     */
    @PreAuthorize("hasAuthority('system:user:save')")
    @PostMapping("/users")
    public Result register(@RequestBody AppUser appUser) {
        return result(appUserService.addAppUser(appUser));
    }

    /**
     * 修改自己的个人信息
     *
     * @param appUser
     */
    @LogAnnotation(module = "修改个人信息")
    @PutMapping("/users/me")
    public Result updateMe(@RequestBody AppUser appUser) {
        return result(appUserService.updateAppUser(appUser));
    }

    @PreAuthorize("hasAuthority('system:user:delete')")
    @DeleteMapping("/users/{id}")
    public Result deleteRole(@PathVariable Long id) {
        return result(appUserService.deleteAppUser(id));
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @LogAnnotation(module = "修改密码")
    @PutMapping(value = "/users/password", params = {"oldPassword", "newPassword"})
    public void updatePassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new IllegalArgumentException("新密码不能为空");
        }

        AppUser user = AppUserUtil.getLoginAppUser();
        appUserService.updatePassword(user.getId(), oldPassword, newPassword);
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id          用户id
     * @param newPassword 新密码
     */
    @LogAnnotation(module = "重置密码")
    @PreAuthorize("hasAuthority('system:user:update')")
    @PutMapping(value = "/users/{id}/password", params = {"newPassword"})
    public void resetPassword(@PathVariable Long id, String newPassword) {
        appUserService.updatePassword(id, null, newPassword);
    }

    /**
     * 管理后台修改用户
     *
     * @param appUser
     */
    @LogAnnotation(module = "修改用户")
    @PreAuthorize("hasAuthority('system:user:update')")
    @PutMapping("/users")
    public void updateAppUser(@RequestBody AppUser appUser) {
        appUserService.updateAppUser(appUser);
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param id      用户id
     * @param roleIds 角色ids
     */
    @LogAnnotation(module = "分配角色")
    @PreAuthorize("hasAuthority('system:user:update')")
    @PostMapping("/users/{id}/roles")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        appUserService.setRoleToUser(id, roleIds);
    }

    /**
     * 获取用户的角色
     *
     * @param id 用户id
     */
    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/users/{id}/roles")
    public Result findRolesByUserId(@PathVariable Long id) {
        return result(appUserService.findRolesByUserId(id));
    }

    @Autowired
    private SmsClient smsClient;

    /**
     * 绑定手机号
     *
     * @param phone
     * @param key
     * @param code
     */
    @LogAnnotation(module = "绑定手机号")
    @PreAuthorize("hasAuthority('system:user:update')")
    @PostMapping(value = "/users/binding-phone")
    public void bindingPhone(String phone, String key, String code) {
        if (StringUtils.isBlank(phone)) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key不能为空");
        }

        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        log.info("绑定手机号，key:{},code:{},username:{}", key, code, loginAppUser.getUsername());

        String value = smsClient.matcheCodeAndGetPhone(key, code, false, 30);
        if (value == null) {
            throw new IllegalArgumentException("验证码错误");
        }

        if (phone.equals(value)) {
            appUserService.bindingPhone(loginAppUser.getId(), phone);
        } else {
            throw new IllegalArgumentException("手机号不一致");
        }
    }

    /**
     * 更新用户启用状态
     *
     * @author zero
     *
     */
    @PreAuthorize("hasAuthority('system:user:update')")
    @PutMapping("/users/enabled")
    public Result updateEnabled(@RequestBody AppUser appUser) {
        return result(appUserService.updateEnabled(appUser));
    }
}
