package com.yupi.usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.UserLoginRequest;
import com.yupi.usercenter.model.domain.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.yupi.usercenter.contant.UserConstant.USER_LOGIN_STATE;


/**
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest==null){
            return null;
        }
        String userAccount=userRegisterRequest.getUserAccount();
        String userPassword=userRegisterRequest.getUserPassword();
        String checkPassword=userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount,userPassword,checkPassword);
    }

    /**
     * 获取当前用户
     * @param request
     * @return
     */
    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request){
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);  //得到对象用户的登录态，获取当前登录用户接口，因为已经存到 session 中
        User currentUser= (User) userObj;
        if(currentUser==null){
            return null;
        }
        long userId=currentUser.getId();
        // TODO 校验用户是否合法
        User user=userService.getById(userId);
        return userService.getSafetyUser(user); //返回一个脱敏的user
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest==null){
            return null;
        }
        String userAccount=userLoginRequest.getUserAccount();
        String userPassword=userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount,userPassword,request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String userName, HttpServletRequest request){

        if(!isAdmin(request))
        {
            return  new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("userName",userName);
        }
        List<User> userList = userService.list();
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public  boolean deleteUser(@RequestBody long id, HttpServletRequest request){
        if(!isAdmin(request))
        {
            return  false;
        }
        if(id<=0){
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //鉴权：仅管理员可以查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) userObj;//有可能为空的
        if(user==null||user.getUserRole()!=ADMIN_ROLE){
            return false;
        }
        return true;
    }
}
