package com.ry.erp.sys.domain;

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
@TableName("sys_loginfo")
public class Loginfo implements Serializable {

    private Integer	id;
    private String	loginname;
    private String	loginip;
    private Date logintime;


}
