package com.ry.erp.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.erp.bus.domain.Outport;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
public interface OutportService extends IService<Outport> {


    void addOutport(Integer id, Integer number, String remark);
}
