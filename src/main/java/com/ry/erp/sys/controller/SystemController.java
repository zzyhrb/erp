package com.ry.erp.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class SystemController {
	
	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "system/index/login";
	}
	
	/**
	 * 跳转到首页
	 */
	@RequestMapping("index")
	public String index() {
		return "system/index/index";
	}

	/**
	 * 主页
	 */
	@RequestMapping("toDeskManager")
	public String toDeskManager(){
		return "system/index/deskManager";
	}

	/**
	 *登录日志
	 */
	@RequestMapping("toLogininfoManager")
	public String toLogininfoManager(){
		return "system/loginfo/loginfoManager";
	}
	/**
	 * 跳转到公告管理
	 *
	 */
	@RequestMapping("toNoticeManager")
	public String toNoticeManager() {
		return "system/notice/noticeManager";
	}

	
}
