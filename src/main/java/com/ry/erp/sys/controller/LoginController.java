package com.ry.erp.sys.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ry.erp.sys.common.ActiverUser;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.common.WebUtils;

/**
 * <p>
 *  登陆前端控制器
 * </p>
 *
 * @author 老雷
 * @since 2019-09-20
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd) {
		Subject subject = SecurityUtils.getSubject();
		
		return null;
		
	}
}

