package com.zero.api.model.user;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class AppUser implements Serializable {

    private static final long serialVersionUID = 611197991672067628L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String phone;
    private Integer sex;
    /**
     * 状态
     */
    private Boolean enabled;
    private String type;
    private Date createTime;
    private Date updateTime;

    @TableField(strategy= FieldStrategy.IGNORED)
    private transient Set<Long> roleIds;
}
