package com.ry.erp.sys.controller;

import com.ry.erp.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@ResponseBody
@Controller("/sys/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;




}
