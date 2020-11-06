package com.ry.erp.sys.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ry.erp.bus.cache.BusinessCacheAspect;
import com.ry.erp.bus.domain.Customer;
import com.ry.erp.bus.domain.Goods;
import com.ry.erp.bus.domain.Provider;
import com.ry.erp.bus.mapper.CustomerMapper;
import com.ry.erp.bus.mapper.GoodsMapper;
import com.ry.erp.bus.mapper.ProviderMapper;
import com.ry.erp.sys.common.SpringUtil;
import com.ry.erp.sys.domain.Dept;
import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.mapper.DeptMapper;
import com.ry.erp.sys.mapper.UserMapper;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * @description:缓存
 * @author zzy
 * @date 2020/11/6 10:40
 */
public class CachePool {


	/**
	 * 所有的缓存数据放到这个CACHE_CONTAINER类似于redis
	 */
	public static volatile Map<String,Object>  CACHE_CONAINER = new HashMap<>();

	/**
	 * 根据key 删除缓存
	 * @param key
	 */
	public static void removeCacheByKey(String key){
		if(CACHE_CONAINER.containsKey(key)){
			CACHE_CONAINER.remove(key);
		}
	}

	/**
	 *清空所有缓存
	 */
	public static void removeAll(){
		CACHE_CONAINER.clear();
	}

	/**
	 * 同步缓存
	 */
	public static void syncData(){
		//同步部门数据
		DeptMapper deptMapper = SpringUtil.getBean(DeptMapper.class);
		List<Dept> deptList = deptMapper.selectList(null);
		for(Dept  dept:deptList){
			CACHE_CONAINER.put("dept:"+dept.getId(),dept);
		}
		//同步用户数据
		UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
		List<User> userList = userMapper.selectList(null);
		for (User user: userList) {
			CACHE_CONAINER.put("user:"+user.getId(),user);
		}
		//同步客户数据
		CustomerMapper customerMapper = SpringUtil.getBean(CustomerMapper.class);
		List<Customer> customerList =customerMapper.selectList(null);
		for(Customer customer:customerList){
			CACHE_CONAINER.put("customer:"+customer.getId(),customer);
		}
		//同步供应商数据
		ProviderMapper providerMapper =SpringUtil.getBean(ProviderMapper.class);
		List<Provider> providerList = providerMapper.selectList(null);
		for(Provider provider:providerList){
			CACHE_CONAINER.put("provider:"+provider.getId(),provider);
		}
		//同步商品数据
		GoodsMapper goodsMapper =SpringUtil.getBean(GoodsMapper.class);
		List<Goods> goodsList = goodsMapper.selectList(null);
		for(Goods goods:goodsList){
			CACHE_CONAINER.put("goods:"+goods.getId(),goods);
		}

	}

}
