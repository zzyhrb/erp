package com.ry.erp.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Sales;
import com.ry.erp.bus.domain.SalesBack;
import com.ry.erp.bus.mapper.GoodsMapper;
import com.ry.erp.bus.mapper.SalesBackMapper;
import com.ry.erp.bus.mapper.SalesMapper;
import com.ry.erp.bus.service.SalesbackService;
import com.ry.erp.bus.vo.SalesBackVo;
import com.ry.erp.bus.vo.SalesVo;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Service
public class SalesBackServiceImpl extends ServiceImpl<SalesBackMapper, SalesBack> implements SalesbackService {
    @Autowired
    private SalesMapper salesMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public void addOutport(Integer id, Integer number, String remark) {
        //根据销售商品id 查询商品销售信息
        Sales sales =this.salesMapper.selectById(id);
        //商品id查询商品
        Goods goods =this.goodsMapper.selectById(sales.getGoodsid());
        goods.setNumber(goods.getNumber()+number);
        this.goodsMapper.updateById(goods);
        //3.添加销售退货单
        SalesBack salesBack =new SalesBack();
        salesBack.setGoodsid(sales.getGoodsid());
        salesBack.setCustomerid(sales.getCustomerid());
        salesBack.setPaytype(sales.getPaytype());
        salesBack.setSalesbacktime(new Date());
        User user = (User) WebUtils.getSession().getAttribute("user");
        salesBack.setOperateperson(user.getName());
        salesBack.setNumber(number);
        salesBack.setSalebackprice(sales.getSaleprice());
        salesBack.setRemark(remark);
        salesBack.setSize(goods.getSize());
        this.getBaseMapper().insert(salesBack);

    }
}
