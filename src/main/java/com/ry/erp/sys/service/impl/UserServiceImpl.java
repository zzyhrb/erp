package com.ry.erp.sys.service.impl;

import com.ry.erp.sys.domain.User;
import com.ry.erp.sys.mapper.UserMapper;
import com.ry.erp.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description:用户服务类实现
 * @author zzy
 * @date 2020/9/25 10:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
