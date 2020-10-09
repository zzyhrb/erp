package com.ry.erp.sys.vo;

import com.ry.erp.sys.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {


    private static  final long serialVersionUID=1L;

    private Integer ids[];// 接收多id
    private Integer page =1;
    private Integer limit =10;


}
