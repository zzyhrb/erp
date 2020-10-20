package com.ry.erp.sys.service.impl;


import com.ry.erp.sys.domain.Permission;
import com.ry.erp.sys.mapper.PermissionMapper;
import com.ry.erp.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @description:服务实现类
 * @author zzy
 * @date 2020/10/16 17:14
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper =this.getBaseMapper();
        //根据权限或菜单id删除关系表里面的数据
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }



}
