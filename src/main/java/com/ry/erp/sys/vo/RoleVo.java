package com.ry.erp.sys.vo;

import com.ry.erp.sys.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    private static final long serialVersionUID=1L;

    private Integer page=1;
    private  Integer limit=10;


}
