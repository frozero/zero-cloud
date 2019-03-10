package com.zero.api.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 */
@Data
public class SysRole implements Serializable {

	private static final long serialVersionUID = -2054359538140713354L;

	@TableId(type = IdType.AUTO)
	private Long id;
	private String code;
	private String name;
	private Date createTime;
	private Date updateTime;
}
