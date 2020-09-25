package com.ry.erp.sys.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
public class WebUtils {

    /**
     * 获取requset
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 获取session
     */

    public static HttpSession getSession() {
        return getRequest().getSession();
    }


}
