package com.ry.erp.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Inport;
import com.ry.erp.bus.domain.Outport;
import com.ry.erp.bus.mapper.GoodsMapper;
import com.ry.erp.bus.mapper.InportMapper;
import com.ry.erp.bus.mapper.OutportMapper;
import com.ry.erp.bus.service.OutportService;
import com.ry.erp.sys.common.WebUtils;
import com.ry.erp.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper,Outport> implements OutportService{

    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addOutport(Integer id, Integer number, String remark) {
        //1.根据进货单id 查询商品进货单信息
        Inport inport =this.inportMapper.selectById(id);
        //2.根据商品id 查询商品信息
        Goods goods =this.goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        this.goodsMapper.updateById(goods);
        //3.添加退货单信息
        Outport entity =new Outport();
        entity.setGoodsid(inport.getGoodsid()); //商品id
        entity.setNumber(number);//退货数量
        User user = (User) WebUtils.getSession().getAttribute("user");
        entity.setOperateperson(user.getName());
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setRemark(inport.getRemark());
        entity.setProviderid(inport.getProviderid());
        this.getBaseMapper().insert(entity);

    }
}
