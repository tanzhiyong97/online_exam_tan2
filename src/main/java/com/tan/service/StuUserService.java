package com.tan.service;

import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import org.springframework.stereotype.Service;

@Service
public interface StuUserService {
    //登录
    public LoginUser findByUsername(String username);

    //判断用户名是否注册过
    public boolean findRegisterUsername(String username);
    //注册用户账号
    public boolean register(LoginUser user);
    //注册学生用户账号信息存储
    public boolean addStudentInformation(StudentInformation user);
    //修改用户密码
    public boolean updatePassword(LoginUser user);
    //查询用户信息
    public StudentInformation findInformationByUsername(String username);
    //修改用户信息
    public boolean updateInformation(StudentInformation user);
}
