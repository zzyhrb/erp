package com.ry.erp.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Sales;
import com.ry.erp.bus.mapper.GoodsMapper;
import com.ry.erp.bus.mapper.SalesMapper;
import com.ry.erp.bus.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {

    @Autowired
     private GoodsMapper goodsMapper;
    @Override
    public boolean save(Sales entity) {
        //根据商品编号查询商品
        Goods goods =goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber()-entity.getNumber());
        goodsMapper.updateById(goods);
        //保存销售信息
        return super.save(entity);
    }

    @Override
    public boolean updateById(Sales entity) {
        //1.当前销售商品数量
        Sales sales =   this.baseMapper.selectById(entity.getId());
        //2.商品当前库存数量
        Goods goods =this.goodsMapper.selectById(sales.getGoodsid());
        //3.库存数量 当前库存-销售数量
        goods.setNumber(goods.getNumber()-sales.getNumber()+entity.getNumber());
        this.goodsMapper.updateById(goods);



        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //1.当前销售商品数量
         Sales sales =   this.baseMapper.selectById(id);
        //2.商品当前库存数量
        Goods goods =this.goodsMapper.selectById(sales.getGoodsid());
        //3.库存数量 当前库存-销售数量
        goods.setNumber(goods.getNumber()-sales.getNumber());
        this.goodsMapper.updateById(goods);

        return super.removeById(id);
    }
}
