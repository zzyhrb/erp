package com.ry.erp.sys.service.impl;

import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.mapper.RoleMapper;
import com.ry.erp.sys.mapper.UserMapper;
import com.ry.erp.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @description:用户服务类实现
 * @author zzy
 * @date 2020/9/25 10:57
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据用户ID删除用户角色中间表的数据
        roleMapper.deleteRoleUserByUid(id);
        //删除用户头[如果是默认头像不删除  否则删除]
        return super.removeById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //根据用户id删除sys_rolw_user数据
        this.roleMapper.deleteRoleUserByUid(uid);
        if(null !=uid && ids.length>0){
            for(Integer rid:ids){
                this.roleMapper.insertUserRole(uid,rid);
            }
        }
    }
}
