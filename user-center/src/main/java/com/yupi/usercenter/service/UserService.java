package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 13303
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-05-05 21:17:24
*/
public interface UserService extends IService<User> {
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
