package com.ry.erp.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper =false)
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept implements Serializable {
    private  static  final  long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer	id;
    private Integer  pid;
    private String	title;
    private  Integer open;
    private String	remark;
    private  String	address;
    private Integer	available;
    private Integer	ordernum;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;




}
