package com.ry.erp.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:业务管理路由器
 * @author zzy
 * @date 2020/10/27 9:21
 */
@Controller
@RequestMapping("/bus")
public class BusinessController {

	/**
	 * 跳转到客户管理
	 */
	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "business/customer/customerManager";
	}
	/**
	 * 跳转到供应商管理
	 */
	@RequestMapping("toProviderManager")
	public String toProviderManager() {
		return "business/provider/providerManager";
	}
	/**
	 * 跳转到商品管理
	 */
	@RequestMapping("toGoodsManager")
	public String toGoodsManager() {
		return "business/goods/goodsManager";
	}

	/**
	 * 跳转商品进货管理
	 * @return
	 */
	@RequestMapping("toInportManager")
	public String toInportManager(){
		return "business/inport/inportManager";
	}

	/**
	 * 跳转商品退货管理
	 * @return
	 */
	@RequestMapping("toOutportManager")
	public String  toOutportManager(){
		return "business/outport/outportManager";
	}

	/**
	 * 商品销售
	 * @return
	 */
	@RequestMapping("tosalesManager")
	private String tosalesManager(){
		return "business/sales/salesManager";
	}

	/**
	 * 销售退货
	 * @return
	 */
	@RequestMapping("tosalesbackManager")
	private String tosalesbackManager(){
		return "business/salesback/outportManager";
	}


}
