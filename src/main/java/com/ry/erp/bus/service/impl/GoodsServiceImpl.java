package com.ry.erp.bus.service.impl;

import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.mapper.GoodsMapper;
import com.ry.erp.bus.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:服务实现类
 * @author zzy
 * @date 2020/10/28 13:58
 */
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

	
	@Override
	public boolean save(Goods entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}
	
	@Override
	public boolean updateById(Goods entity) {
		// TODO Auto-generated method stub
		return super.updateById(entity);
	}
	
	@Override
	public boolean removeById(Serializable id) {
		// TODO Auto-generated method stub
		return super.removeById(id);
	}
	
	@Override
	public Goods getById(Serializable id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}
}
