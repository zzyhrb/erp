package com.ry.erp.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ry.erp.sys.cache.CacheAspect;
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
@TableName("bank")
public class Ledger implements Serializable {

    private  static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;
    private  String	name;
    private  Float	money;
    private  Integer start;
    private  String	type;
    private  String	descrip;
    @TableField(exist = false)
    private  double smoney;

}
