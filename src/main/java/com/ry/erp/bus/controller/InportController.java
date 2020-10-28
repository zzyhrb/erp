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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
