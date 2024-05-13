package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//�û��������

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
        String userPassword="12345678";
        String checkPassword="12345678";
        long result= userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

//        userAccount="su";
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="HsuRosy";
//        userPassword="123456";
//        checkPassword="123456";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="Hsu$Rosy";
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="HsuRosy";
//        userPassword="12345678";
//        checkPassword="123456789";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="dogHsu";
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertEquals(-1,result);
//
//        userAccount="yupi";
//        userPassword="12345678";
//        checkPassword="12345678";
//        result= userService.userRegister(userAccount,userPassword,checkPassword);
//        Assertions.assertTrue(result>0);
//        Assertions.assertEquals(-1,result);
    }
}