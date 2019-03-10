package com.zero.api.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜單
 */
@Data
public class SysMenu implements Serializable {

	private static final long serialVersionUID = -9017577308755865558L;

	private Long id;
	private Long parentId;
	private String code;
	private String name;
	private String desc;
	private int sort;
	private int isLeaf;
	private Date createTime;
	private Date updateTime;
	private Long permissionId;
	private String permission;
	List<SysMenu> children;
}
