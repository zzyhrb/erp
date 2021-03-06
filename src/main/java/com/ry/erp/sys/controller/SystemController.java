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

	/**
	 * 婚礼账本
	 * @return
	 */
	@RequestMapping("toLedger")
	public String toLedger(){
		return "system/ledger/LedgerManager";
	}


	/**
	 * 跳转到部门管理
	 *
	 */
	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/deptManager";
	}
	/**
	 * 部门左侧树
	 * @return
	 */
	@RequestMapping("toDeptLeft")
	public String toDeptLeft(){
		return "system/dept/deptLeft";
	}

	/**
	 * 部门右侧列表显示
	 * @return
	 */
	@RequestMapping("toDeptRight")
	public String toDeptRight(){
		return "system/dept/deptRight";
	}

	/**
	 *菜单管理
	 * @return
	 */
	@RequestMapping("toMenuManager")
	public String toMenuManager(){
		return "system/menu/menuManager";
	}

	/**
	 *菜单左侧显示
	 * @return
	 */
	@RequestMapping("toMenuLeft")
	public String  toMenuLeft(){
		return "system/menu/menuLeft";
	}

	/**
	 * 菜单右侧显示
	 * @return
	 */
	@RequestMapping("toMenuRight")
	public String  toMenuRight(){
		return "system/menu/menuRight";
	}

	/**
	 * 权限管理
	 * @return
	 */
	@RequestMapping("toPermissionManager")
	public String toPermissionManager(){
		return "system/permision/permisionManager";
	}

	/**
	 * 权限管理左侧
	 * @return
	 */

	@RequestMapping("toPermisionLeft")
	public String toPermisionLeft(){
		return "system/permision/permisionLeft";
	}

	/**
	 * 权限管理右侧
	 * @return
	 */

	@RequestMapping("toPermisionRight")
	public String toPermisionRight(){
		return "system/permision/permisionRight";
	}

	/***
	 * 角色管理
	 * @return
	 */
	@RequestMapping("toRoleManager")
	public String toRoleManager(){
		return "system/role/roleManager";
	}

	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping("toUserManager")
	public String toUserManager(){
		return "system/user/userManager";
	}
	/**
	 * 跳转到缓存管理
	 *
	 */
	@RequestMapping("toCacheManager")
	public String toCacheManager() {
		return "system/cache/cacheManager";
	}

	@RequestMapping("toPassWord")
	public String toPassWord(){
		return "system/password/passwordManager";
	}
}
