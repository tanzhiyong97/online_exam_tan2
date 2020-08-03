package com.tan.service.Impl;

import com.tan.mapper.StuUserMapper;
import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.service.StuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuUserServiceImpl implements StuUserService {
    //属性注入
    @Autowired
    private StuUserMapper stuUserMapper;


    //登录验证
    @Override
    public LoginUser findByUsername(String username) {
        return stuUserMapper.findByUsername(username);
    }

    //注册判断用户名是否存在
    @Override
    public boolean findRegisterUsername(String username) {
        if (stuUserMapper.findRegisterUsername(username) == null) {
            return false;
        } else
            return true;
    }


    //判断注册是否增加成功
    @Override
    public boolean register(LoginUser user) {
        int addRegister = 0;
        addRegister = stuUserMapper.register(user);
        // System.out.println("appregister:"+appregister);
        if (addRegister == 0) {
            return false;
        } else {
            return true;
        }
    }

    //查询用户信息
    @Override
    public StudentInformation findInformationByUsername(String username) {
        return stuUserMapper.findInformationByUsername(username);
    }

    //增加用户信息
    @Override
    public boolean addStudentInformation(StudentInformation user) {
        int appregister = 0;
        appregister = stuUserMapper.addStudentInformation(user);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }

    //修改用户密码
    @Override
    public boolean updatePassword(LoginUser user) {
        if (stuUserMapper.updatePassword(user) == 0) {
            return false;
        } else
            return true;
    }

    //修改用户信息
    @Override
    public boolean updateInformation(StudentInformation user) {
        if (stuUserMapper.updateInformation(user) == 0) {
            return false;
        } else
            return true;
    }

}