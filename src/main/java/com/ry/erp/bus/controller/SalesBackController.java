package com.ry.erp.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.bus.domain.Customer;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Sales;
import com.ry.erp.bus.domain.SalesBack;
import com.ry.erp.bus.service.CustomerService;
import com.ry.erp.bus.service.GoodsService;
import com.ry.erp.bus.service.SalesbackService;
import com.ry.erp.bus.vo.SalesBackVo;
import com.ry.erp.bus.vo.SalesVo;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
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
@RequestMapping("salseback")
public class SalesBackController {
    @Autowired
    private SalesbackService salesbackService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("loadAllsalesback")
    public DataGridView loadAllsalesback(SalesBackVo salesBackVo){

        Page<SalesBack> page =new Page<>(salesBackVo.getPage(),salesBackVo.getLimit());
        QueryWrapper<SalesBack> queryWrapper =new QueryWrapper<>();

        queryWrapper.ge(salesBackVo.getStartTime()!=null,"outputtime",salesBackVo.getStartTime());
        queryWrapper.le(salesBackVo.getStartTime()!=null,"outputtime",salesBackVo.getEndTime());
        this.salesbackService.page(page,queryWrapper);
        List<SalesBack> list =page.getRecords();
        for(SalesBack salesBack:list){
            Customer customer =this.customerService.getById(salesBack.getCustomerid());
            if(null != customer){
                salesBack.setCustomerName(customer.getCustomername());
            }
            Goods goods = this.goodsService.getById(salesBack.getGoodsid());
            if(null !=goods){
                salesBack.setGoodsName(goods.getGoodsname());
            }


        }


        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 退货
     * @return
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Integer id,Integer number,String remark){
        try {
            this.salesbackService.addOutport(id,number,remark);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERROR;
        }

    }

}
