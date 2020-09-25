package com.ry.erp.sys.controller;


import com.ry.erp.sys.common.ActiverUser;
import com.ry.erp.sys.common.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ry.erp.sys.common.ResultObj;

/**
 * @description:登录前端控制器
 * @author zzy
 * @date 2020/9/25 10:00
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token =new UsernamePasswordToken(loginname,pwd);
		try {
			subject.login(token);
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user",activerUser.getUser());

			return ResultObj.LOGIN_SUCCESS;
		}catch (AuthenticationException e){
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}

		
	}
}

