package com.ry.erp.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    @TableId(value = "id", type= IdType.AUTO)
    private Integer	id;
    /**
     * 父级编号
     */
    private Integer	pid;
    /**
     * 权限类型
     */
    private String	type;
    /**
     * 名称
     */
    private String	title;
    /**
     * 权限编码
     */
    private String	percode;
    /**
     * 图标
     */
    private String	icon;
    /**
     * 地址
     */
    private String	href;
    /**
     * 目标
     */
    private String	target;
    /**
     * 是否展开[0不展开1展开]
     */
    private Integer	open;
    /**
     * 排序码【为了调事显示顺序】
     */
    private Integer	ordernum;
    /**
     * 状态【0不可用1可用】
     */
    private Integer	available;



}
