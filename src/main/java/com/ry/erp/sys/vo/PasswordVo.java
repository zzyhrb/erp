package com.ry.erp.sys.vo;

import com.ry.erp.sys.domain.Password;
import lombok.Data;

@Data
public class PasswordVo extends Password {

    private static final long serialVersionUID=1L;

    private Integer page=1;

    private Integer limit=10;
}
