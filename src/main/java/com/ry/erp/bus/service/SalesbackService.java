package com.ry.erp.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.erp.bus.domain.Sales;
import com.ry.erp.bus.domain.SalesBack;
import com.ry.erp.bus.vo.SalesBackVo;
import com.ry.erp.bus.vo.SalesVo;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
public interface SalesbackService extends IService<SalesBack> {
    
    void addOutport(Integer id, Integer number, String remark);
    
    
}
