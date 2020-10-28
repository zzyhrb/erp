package com.ry.erp.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_inport")
public class Inport implements Serializable {

    private static  final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer	id;
    private String	paytype;
    private Date inporttime;
    private String	operateperson;
    private Integer	number;
    private String	remark;
    private Double	inportprice;
    private Integer	providerid;
    private Integer	goodsid;

    @TableField(exist = false)
    private String providername; //供应商名称
    @TableField(exist = false)
    private String goodsname; //商品
    @TableField(exist = false)
    private String size;


}
