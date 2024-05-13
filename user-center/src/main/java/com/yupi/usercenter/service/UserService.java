package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 13303
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2024-05-05 21:17:24
 */
public interface UserService extends IService<User> {



    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);
    /**
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 新用户id
     */

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

}
