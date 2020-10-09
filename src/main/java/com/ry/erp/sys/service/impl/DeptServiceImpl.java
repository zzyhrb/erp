package com.ry.erp.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.erp.sys.domain.Dept;
import com.ry.erp.sys.mapper.DeptMapper;
import com.ry.erp.sys.service.DeptService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {


    @Override
    public Dept getOne(Wrapper<Dept> queryWrapper, boolean throwEx) {
        return super.getOne(queryWrapper, throwEx);
    }

    @Override
    public boolean update(Dept entity, Wrapper<Dept> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
