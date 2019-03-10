package com.zero.user.server.service;


import com.zero.api.model.user.SysMenu;

import java.util.List;

public interface SysMenuService {

	List<SysMenu> listAll();

	void refreshCache();
}
