package com.ry.erp.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.erp.sys.domain.Password;
import com.ry.erp.sys.mapper.PasswordMapper;
import com.ry.erp.sys.service.PasswordService;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl extends ServiceImpl<PasswordMapper, Password> implements PasswordService {
}
