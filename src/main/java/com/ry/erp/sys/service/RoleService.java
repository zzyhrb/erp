package com.ry.erp.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.erp.sys.domain.Role;

import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色id 查询当前角色拥有的权限或菜单id
     */
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色 和菜单关系数据
     * @param rid
     * @param ids
     */
    public void saveRolePermission(Integer rid, Integer[] ids);


    List<Integer> queryUserRoleIdsByUid(Integer id);
}
