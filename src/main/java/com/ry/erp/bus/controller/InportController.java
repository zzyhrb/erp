package com.ry.erp.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Inport;
import com.ry.erp.bus.domain.Provider;
import com.ry.erp.bus.service.GoodsService;
import com.ry.erp.bus.service.InportService;
import com.ry.erp.bus.service.ProviderService;
import com.ry.erp.bus.vo.InportVo;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@RestController
@RequestMapping("inport")
public class InportController {

    @Autowired
    private InportService inportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     * @param inportVo
     * @return
     */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo){

        IPage<Inport> page =new Page<>(inportVo.getPage(),inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq(inportVo.getProviderid()!=null && inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        queryWrapper.eq(inportVo.getGoodsid()!=null && inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        queryWrapper.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getInporttime());
        queryWrapper.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        queryWrapper.orderByDesc("inporttime");
        this.inportService.pageMaps(page,queryWrapper);
        this.inportService.page(page, queryWrapper);
        List<Inport> records = page.getRecords();
        for (Inport inport : records) {
            Provider provider = this.providerService.getById(inport.getProviderid());
            if(null !=provider){
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods =this.goodsService.getById(inport.getGoodsid());
            if(null !=goods){
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param inportVo
     * @return
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo){
        try {
            inportVo.setInporttime(new Date());
            User  user = (User) WebUtils.getSession().getAttribute("user");
            inportVo.setOperateperson(user.getName());
            this.inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo){
        try {
            this.inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     *删除
     * @return
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try{
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }



}
