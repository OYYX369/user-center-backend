package com.yupi.usercenter.service;

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
        String userAccount="oyyx";
        String userPassword="12345678";
        String checkPassword="12345678";
        String planetCode="5004";
        long result= userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
        Assertions.assertEquals(-1,result);

    }
}