package com.ry.erp.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:角色实体类
 * @author zzy
 * @date 2020/10/19 15:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable {

    private  static final  Long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer	id;
    private String	name;
    private String	remark;
    private Integer	available;
    private Date createtime;




}
