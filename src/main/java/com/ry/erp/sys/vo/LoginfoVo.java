package com.ry.erp.sys.vo;

import com.ry.erp.sys.domain.Loginfo;
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
public class LoginfoVo extends Loginfo {

    private static final long seralVersionUID=1L;

    private Integer page;
    private Integer limit;
    private Integer[] ids; //接收多个id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date endTime;

}
