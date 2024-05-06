package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.service.UserService;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author ousir
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-05-05 21:17:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        //1.校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            // TODO
            return -1;
        }
        if(userAccount.length()<4){
            return -1;
        }
        if(userPassword.length()<8||checkPassword.length()<8){
            return -1;
        }
        //账户不能包含特殊字符
        String validPattern="\\pP|\\pS|\\s+";
        Matcher matcher= Pattern.compile(validPattern).matcher(userAccount);
        if(!matcher.find()){
            return -1;
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }
        //用户不能重复
        //这里有个查询数据库的操作，如果账户包含了特殊字符就不用查了，因此把这个操作放到最后节省了一点性能
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count=userMapper.selectCount(queryWrapper);
        if(count>0){
            return -1;
        }
        //2.加密
        final String SALT="yupi";
        String encryptPassword= DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());

        //3.插入数据
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult=this.save(user);
        if(!saveResult){
            return -1;
        }
        return user.getId();
    }
}




