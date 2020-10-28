package com.ry.erp.bus.vo;

import com.ry.erp.bus.domain.Goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {

	/*
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private Integer page = 1;
	private Integer limit = 10;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date endTime;
}
