package com.tan.mapper;

import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StuUserMapper {

    //登录
    public LoginUser findByUsername(String username);

    //判断用户名是否注册过
    public LoginUser findRegisterUsername(String username);
    //注册用户账号
    public int register(LoginUser user);
    //注册学生用户账号信息存储
    public int addStudentInformation(StudentInformation user);
    //修改用户密码
    public int updatePassword(LoginUser user);
    //查询用户信息
    public StudentInformation findInformationByUsername(String username);
    //修改用户信息
    public int updateInformation(StudentInformation user);
}
