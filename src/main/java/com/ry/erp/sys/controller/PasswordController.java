package com.ry.erp.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.domain.Password;
import com.ry.erp.sys.service.PasswordService;
import com.ry.erp.sys.vo.PasswordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("password")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @RequestMapping("loadAllPwd")
    public DataGridView loadAllPwd(PasswordVo passwordVo){
        IPage<Password> page =new Page<>(passwordVo.getPage(),passwordVo.getLimit());
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(passwordVo.getTitle()),"title",passwordVo.getTitle());
        this.passwordService.page(page,queryWrapper);

        return new DataGridView(page.getTotal(),page.getRecords());



    }

    @RequestMapping("addPwd")
    public ResultObj  addPwd(PasswordVo passwordVo){
        try {
            this.passwordService.save(passwordVo);
            return  ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("delPwd")
    public ResultObj delPwd(PasswordVo passwordVo){
        try {
            this.passwordService.removeById(passwordVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }

    @RequestMapping("updatePwd")
    public ResultObj updatePwd(PasswordVo passwordVo){
        try {
            this.passwordService.updateById(passwordVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
