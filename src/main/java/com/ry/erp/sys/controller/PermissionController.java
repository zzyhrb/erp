package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.Constast;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.common.TreeNode;
import com.ry.erp.sys.domain.Permission;
import com.ry.erp.sys.service.PermissionService;
import com.ry.erp.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:权限管理控制类
 * @author zzy
 * @date 2020/10/19 9:25
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**************权限管理开始*************************/

    @RequestMapping("loadPermisionManagerLeftTreeJson")
    public DataGridView loadPermisionManagerLeftTreeJson(PermissionVo permissionVo){
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("type", Constast.TYPE_MNEU);
        List<Permission> list =  this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes =new ArrayList<>();
        for(Permission permission :list){
            Boolean spread = permission.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadAllPermision")
    public DataGridView loadAllPermision(PermissionVo permissionVo){
        IPage<Permission> page =new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("type",Constast.TYPE_PERMISSION);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        queryWrapper.eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page,queryWrapper);

        return new DataGridView(page.getTotal(),page.getRecords());

    }

    /**
     * 加载最大排序码
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadPermissionMaxOrderNum")
    public Map<String,Object> loadPermissionMaxOrderNum(){
        Map<String,Object> map =new HashMap<String, Object>();
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page =new Page<>(1,1);
        List<Permission> list = this.permissionService.page(page,queryWrapper).getRecords();
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else{
            map.put("vale",1);
        }
        return map;
    }

    /**
     * 添加
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("addPermission")
    public ResultObj addPermission(PermissionVo permissionVo) {
        try {
            permissionVo.setType(Constast.TYPE_PERMISSION);// 设置添加类型
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     * @param permissionVo
     * @return
     */
    @RequestMapping("updatePermission")
    public ResultObj updatePermission(PermissionVo permissionVo){
        try {
            this.permissionService.updateById(permissionVo);
            return  ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }


    }


    /**
     * 删除
     */
    @RequestMapping("deletePermission")
    public ResultObj deletePermission(PermissionVo permissionVo){
        try{
            this.permissionService.removeById(permissionVo.getId());
            return  ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;

        }


    }


    /**************权限管理结束*************************/
}
