package com.ry.erp.bus.vo;

import com.ry.erp.bus.domain.SalesBack;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalesBackVo extends SalesBack {
    private static final  long serialVersionUID=1L;

    private Integer page =1;
    private Integer limit =10;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date startTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH-mm-ss")
    private Date endTime;


}
