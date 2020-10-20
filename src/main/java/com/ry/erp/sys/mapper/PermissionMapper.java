package com.ry.erp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.erp.sys.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:登录日志信息接口
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    public void deleteRolePermissionByPid(@Param("id") Serializable id);

}
