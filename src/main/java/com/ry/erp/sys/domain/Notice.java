package com.ry.erp.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_notice")
public class Notice {



    int	id
    varchar	title
    text	content
    datetime	createtime
    varchar	opername

}
