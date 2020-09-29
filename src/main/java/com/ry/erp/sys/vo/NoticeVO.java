package com.ry.erp.sys.vo;

import com.ry.erp.sys.domain.Notice;
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
public class NoticeVO extends Notice {

    private static  final long serialVersionUID=1L;

    private Integer ids[];// 接收多id
    private Integer page =1;
    private Integer limit =10;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;



}
