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
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword
     * planetCode 星球编号
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);

    /**
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 新用户id
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);
    /**
     *  注销用户
     */
    int  userLogout(HttpServletRequest request);
}
