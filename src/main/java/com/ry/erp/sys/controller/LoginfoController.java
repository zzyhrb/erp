package com.ry.erp.sys.controller;


import com.ry.erp.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.domain.Loginfo;
import com.ry.erp.sys.service.LoginfoService;
import com.ry.erp.sys.vo.LoginfoVo;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author: zzy
 * @Date: $ $
 * @Description:登录日志前端控制器
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    /**
     * @description:查询全部信息
     * @author zzy
     * @date 2020/9/27 9:57
     */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo){
        IPage<Loginfo> page=new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip",loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.loginfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * @description:根据id 删除信息
     * @author zzy
     * @date 2020/9/27 14:14
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }

    }

    /**
     * @description:批量删除
     * @author zzy
     * @date 2020/9/27 14:20
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo){

        try{
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.loginfoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }










    }

}
