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
@TableName("bus_sales")
public class Sales  implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer customerid;
    private String paytype;
    private Date salestime;
    private String operateperson;
    private Integer number;
    private String remark;
    private Double saleprice;
    private Integer goodsid;
    @TableField(exist=false)
    private  String customerName;
    @TableField(exist=false)
    private String goodsName;

}
