package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.erp.sys.common.*;
import com.ry.erp.sys.domain.Permission;
import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.service.PermissionService;
import com.ry.erp.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadindexLeftMenuJson(PermissionVo permissionVo){

        //查询所有菜单
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        //设置只能查询菜单
        queryWrapper.eq("type",Constast.TYPE_MNEU);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list=null;
        if(user.getType()==Constast.USER_TYPE_SUPER) {
            list = permissionService.list(queryWrapper);
        }else {
            //根据用户ID+角色+权限去查询
            list = permissionService.list(queryWrapper);
        }

        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission p : list) {
            Integer id=p.getId();
            Integer pid=p.getPid();
            String title=p.getTitle();
            String icon=p.getIcon();
            String href=p.getHref();
            Boolean spread=p.getOpen()== Constast.OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }
        //构造层级关系
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(list2);
    }

}
