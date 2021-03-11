package com.ry.erp.sys.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zy_password")
@ToString
public class Password implements Serializable {

    private Integer id;

    private String  title;

    private String account;
}
