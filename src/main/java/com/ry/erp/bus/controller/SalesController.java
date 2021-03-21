package com.ry.erp.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.erp.bus.domain.Customer;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Sales;
import com.ry.erp.bus.service.CustomerService;
import com.ry.erp.bus.service.GoodsService;
import com.ry.erp.bus.service.SalesService;
import com.ry.erp.bus.vo.SalesVo;
import com.ry.erp.sys.common.DataGridView;
import com.ry.erp.sys.common.ResultObj;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("sales")
public class SalesController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 查询
     * @param salesVo
     * @return
     */
    @RequestMapping("loadAllsales")
    public DataGridView loadAllsales(SalesVo salesVo){

        IPage<Sales> page = new Page<>(salesVo.getPage(),salesVo.getLimit());
        QueryWrapper<Sales> queryWrapper =new QueryWrapper<>();
        queryWrapper.ge(salesVo.getStartTime()!=null,"outputtime",salesVo.getStartTime());
        queryWrapper.le(salesVo.getStartTime()!=null,"outputtime",salesVo.getEndTime());
        this.salesService.page(page,queryWrapper);
        List<Sales> list =page.getRecords();
        for(Sales sales:list){
            Customer customer =this.customerService.getById(sales.getCustomerid());
            if(null != customer){
                sales.setCustomerName(customer.getCustomername());
            }
            Goods goods = this.goodsService.getById(sales.getGoodsid());
            if(null !=goods){
                sales.setGoodsName(goods.getGoodsname());
            }


        }

        return new DataGridView(page.getTotal(),page.getRecords());

    }

    /**
     * 添加销售商品
     * @param salesVo
     * @return
     */
    @RequestMapping("addSales")
    public ResultObj addSales(SalesVo salesVo){
        try{
            salesVo.setSalestime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            salesVo.setOperateperson(user.getName());
            salesService.save(salesVo);
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
    @RequestMapping("updateSales")
    public ResultObj updateSales(SalesVo salesVo){
        try {
            this.salesService.updateById(salesVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("deleteSales")
    public  ResultObj deleteSales(Integer id){
        try {
            this.salesService.removeById(id);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}
