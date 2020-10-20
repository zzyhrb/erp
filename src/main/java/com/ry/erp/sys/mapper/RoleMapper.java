package com.ry.erp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.erp.sys.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zzy
 * @Date: $ $
 * @Description:
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色ID删除sys_role_permission
     * @param id
     */
    void deleteRolePermissionByRid(Serializable id);

    /**
     * 根据角色ID删除sys_role_user
     * @param id
     */
    void deleteRoleUserByRid(Serializable id);

    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param pid
     */
    void saveRolePermission(@Param("rid")Integer rid, @Param("pid")Integer pid);


}
