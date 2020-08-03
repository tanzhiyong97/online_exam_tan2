package com.tan.mapper;

import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.pojo.TeacherInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminUserMapper {

    /**
     * 返回前端页面
     * 查询人员管理（admin，teacher，student)
     */
    //返回所有管理员用户信息给前端页面
    public List<LoginUser> findAdminByRoles(String roles);
    //返回所有教师用户信息给前端页面
    public List<TeacherInformation> findTeacherByRoles(String roles);
    //返回所有学生用户信息给前端页面
    public List<StudentInformation> findStudentByRoles(String roles);


    /**
     *对管理员用户的操作
     */
    //登录
    public LoginUser findByUsername(String username);
    //根据id返回管理员用户对象
    public LoginUser findAdminById(Integer id);
    //增加各种登录用户
    public void addLoginUser(LoginUser loginUser);
    //修改各种用户数据
    public void updateLoginUser(LoginUser loginUser);
    //删除管理员数据
    public void deleteLoginUser(Integer[] id);


    /**
     *对教师对象的操作
     */
    //根据username返回教师用户对象
    public TeacherInformation findTeacherByUsername(String username);
    public TeacherInformation findTeacherById(Integer id);
    //根据id查找教师详细表的teacherID
    public int findTeacherIdById(Integer id);
    //增加教师用户详细信息
    public void addTeacherInformation(TeacherInformation teacherInformation);
    //修改教师用户数据
    public void updateTeacherInformation(TeacherInformation teacherInformation);
    //删除教师用户数据
    public void deleteTeacher(Integer[] id);

    /**
     * 对学生对象的操作
     */
    //根据id返回学生用户对象
    public LoginUser findStudentById(Integer id);
    //修改学生用户数据
    public void updateStudentInformation(LoginUser loginUser);
}
