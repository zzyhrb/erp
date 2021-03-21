package com.ry.erp.sys.controller;



import com.ry.erp.sys.common.HttpServletContextKit;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.ry.erp.sys.common.ActiverUser;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.Loginfo;
import com.ry.erp.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ry.erp.sys.common.ResultObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @description:登录前端控制器
 * @author zzy
 * @date 2020/9/25 10:00
 */
@RestController
@RequestMapping("login")
public class LoginController {
	@Value(value = "${kvf.login.authcode.dynamic}")
	private boolean isDynamic;

	@Value(value = "${kvf.login.authcode.enable}")
	private boolean needAuthCode;

	@Autowired
	private LoginfoService loginfoService;

	@RequestMapping("login")
	public ResultObj login( String loginname, String pwd, String vercode) {

		// 只有开启了验证码功能才需要验证。可在yml配置kvf.login.authcode.enable来开启或关闭
		if (needAuthCode) {
			// 验证码校验
			HttpServletRequest request = HttpServletContextKit.getHttpServletRequest();
			if (!CaptchaUtil.ver(vercode, request)) {
				CaptchaUtil.clear(request);  // 清除session中的验证码
				return ResultObj.LOGIN_ERROR_CODE;
			}
		}





		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token =new UsernamePasswordToken(loginname,pwd);
		try {
			subject.login(token);
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user",activerUser.getUser());
			//添加登录日志
			Loginfo entity =new Loginfo();
			entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
			entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
			entity.setLogintime(new Date());
			loginfoService.save(entity);
			return ResultObj.LOGIN_SUCCESS;
		}catch (AuthenticationException e){
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}


	}

	/**
	 * 图片验证码
	 */
	@GetMapping(value = "captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 可在yml配置kvf.login.authcode.dynamic切换动静态图片验证码，默认静态
		// 其它验证码样式可前往查看：https://gitee.com/whvse/EasyCaptcha
		if (isDynamic) {
			CaptchaUtil.out(new GifCaptcha(), request, response);
		} else {
			CaptchaUtil.out(request, response);
		}
	}

}

