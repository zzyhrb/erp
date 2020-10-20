package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.*;
import com.ry.erp.sys.domain.Permission;
import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.service.PermissionService;
import com.ry.erp.sys.vo.PermissionVo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 菜单控制器
 * @author zzy
 * @date 2020/10/13 15:09
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 加载左侧菜单树json
     * @param permissionVo
     * @return
     */
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

/****************菜单管理开始****************/
    /**
     * 菜单左侧json 树
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", Constast.TYPE_MNEU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission menu : list) {
            Boolean spread=menu.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询列表信息
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadAllmenu")
    public DataGridView loadAllmenu(PermissionVo permissionVo){
        IPage<Permission> page =new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId()!=null ,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        queryWrapper.eq("type",Constast.TYPE_MNEU); //只查询菜单
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());

    }

    /**
     * 查询最大排序码
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuMaxOrderNum")
    public Map<String,Object> loadMenuMaxOrderNum(PermissionVo permissionVo){
        Map<String,Object> map =new HashMap<String,Object>();
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page = new Page<>(1,1);
        List<Permission> list = this.permissionService.page(page,queryWrapper).getRecords();
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else{
            map.put("value",1);
        }
        return map;

    }


    /**
     * 添加
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(PermissionVo permissionVo){
        try{
            permissionVo.setType(Constast.TYPE_MNEU);
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 修改菜单信息
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo){
        try{
            this.permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询当前id菜单有没有子菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String,Object> checkMenuHasChildrenNode(PermissionVo permissionVo){
        Map<String,Object> map =new HashMap<String,Object>();
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("pid",permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if(list.size()>0){
            map.put("value",true);
        }else{
            map.put("value",false);
        }
        return map;
    }

    /**
     * 删除菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo){
        try{
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }

/****************菜单管理结束****************/

}
