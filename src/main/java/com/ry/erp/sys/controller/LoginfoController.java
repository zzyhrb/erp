package com.ry.erp.sys.controller;


import com.alibaba.excel.EasyExcel;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author: zzy
 * @Date: $ $
 * @Description:登录日志前端控制器
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {
    public static  String UPLOAD_PATH="E:/upload/";//默认值
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

    /**
     * 导出登录日
     */
    @RequestMapping("exportCustomer")
    public void exportCustomer(LoginfoVo loginfoVo, HttpServletResponse response)throws IOException {
       // String fileName =UPLOAD_PATH + "登录日志.xlsx";
        QueryWrapper<Loginfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip",loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        List<Loginfo> list = this.loginfoService.list(queryWrapper);


        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("登录日志", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Loginfo.class).sheet("模板").doWrite(list);

       // EasyExcel.write(fileName, Loginfo.class).sheet("模板").doWrite(list);


    }




}
