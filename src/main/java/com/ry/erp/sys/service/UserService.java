package com.ry.erp.sys.service;

import com.ry.erp.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description:用户服务类
 * @author zzy
 * @date 2020/9/25 10:57
 */

public interface UserService extends IService<User> {

    /**
     * 保存用户与角色之间关系
     * @param uid
     * @param ids
     */
    void saveUserRole(Integer uid, Integer[] ids);
}
