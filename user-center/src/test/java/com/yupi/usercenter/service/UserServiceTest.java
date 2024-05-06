package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//用户服务测试

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser()
    {

    }

    @Test
    void userRegister() {
        String userAccount="yupi";
        String userPassword="";//密码不能为空
        String checkPassword="123456";
        long result= userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="su";//账户不小于4位
        userPassword="12345678";
        checkPassword="12345678";
        result= userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount="HsuRosy";
        userPassword="123456";//密码不小于8位
        checkPassword="123456";
        result= userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

//        userAccount="Hsu Rosy";//不能包含特殊字符
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="HsuRosy";
//        userPassword="12345678";
//        checkPassword="123456789";//校验密码要和密码相同
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="dogHsu";//账户不能重复
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="yupi";//正确的一组
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        //Assertions.assertTrue(result>0);
//        Assertions.assertEquals(-1,result);
    }
}