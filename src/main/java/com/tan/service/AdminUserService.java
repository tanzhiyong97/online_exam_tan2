package com.tan.service;

import com.tan.pojo.ExamSubject;
import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.pojo.TeacherInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminUserService {

    //登录
    public LoginUser findByUsername(String username);


    //根据id返回管理员用户对象
    public LoginUser findAdminById(Integer id);
    public TeacherInformation findTeacherById(Integer id);
    public void deleteLoginUser(Integer[] id);
    public void deleteTeacher(Integer[] id);

    //返回所有管理员用户信息给前端页面
    public List<LoginUser> findAdminByRoles();

    //返回所有教师用户信息给前端页面
    public List<TeacherInformation> findTeacherByRoles();

    public TeacherInformation findTeacherByUsername(String username);

//    //增加各种登录用户
//    public void addLoginUser(LoginUser loginUser);
//
//    //修改各种用户数据
//    public void updateLoginUser(LoginUser loginUser);
    //在业务层将增加和修改业务合并起来
    public void saveAdmin(LoginUser loginUser);

    //根据id查找教师详细表的teacherID
    public TeacherInformation findTeacherIdByUsername(String username);

//    //增加教师用户详细信息
//    public void addTeacherInformation(TeacherInformation teacherInformation);
//    //修改教师用户数据
//    public void updateTeacherInformation(TeacherInformation teacherInformation);
    //在业务层将增加和修改业务合并起来
    public void saveTeacher(TeacherInformation teacherInformation);

    //根据id返回学生用户对象
    public LoginUser findStudentById(Integer id);
    //返回所有学生用户信息给前端页面
    public List<StudentInformation> findStudentByRoles();
    //修改学生用户数据
    public void updateStudentUser(LoginUser loginUser);
}
