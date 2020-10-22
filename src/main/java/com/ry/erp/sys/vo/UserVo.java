package com.ry.erp.sys.vo;

import com.ry.erp.sys.cache.CacheAspect;
import com.ry.erp.sys.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private static final long serialVersionUID=1L;

    private Integer page=1;

    private Integer limit=10;



}
