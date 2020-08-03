package com.tan.service.Impl;

import com.tan.mapper.AdminUserMapper;
import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.pojo.TeacherInformation;
import com.tan.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public LoginUser findByUsername(String username) {
        return adminUserMapper.findByUsername(username);
    }

    @Override
    public LoginUser findAdminById(Integer id) {
        return adminUserMapper.findAdminById(id);
    }

    @Override
    public TeacherInformation findTeacherById(Integer id) {
        return adminUserMapper.findTeacherById(id);
    }

    @Override
    public TeacherInformation findTeacherByUsername(String username) {
        return adminUserMapper.findTeacherByUsername(username);
    }

    @Override
    public TeacherInformation findTeacherIdByUsername(String username) {
        return adminUserMapper.findTeacherByUsername(username);
    }

    @Override
    public void deleteLoginUser(Integer[] id) {
        adminUserMapper.deleteLoginUser(id);
    }

    @Override
    public List<LoginUser> findAdminByRoles() {
        return adminUserMapper.findAdminByRoles("admin");
    }

    @Override
    public List<TeacherInformation> findTeacherByRoles() {
        return adminUserMapper.findTeacherByRoles("teacher");
    }


//    @Override
//    public int findTeacherIdById(Integer id) {
//        return adminUserMapper.findTeacherIdById(id);
//    }

    @Override
    public void saveAdmin(LoginUser loginUser) {
        if(loginUser.getId()==null){
            adminUserMapper.addLoginUser(loginUser);
        }else{
            adminUserMapper.updateLoginUser(loginUser);
        }
    }

    @Override
    public void saveTeacher(TeacherInformation teacherInformation) {
        if(teacherInformation.getTeacherId()==null){
            adminUserMapper.addTeacherInformation(teacherInformation);
        }else {
            Integer teacherId = adminUserMapper.findTeacherIdById(teacherInformation.getTeacherId());
            teacherInformation.setTeacherId(teacherId);
            adminUserMapper.updateTeacherInformation(teacherInformation);
        }
    }

    @Override
    public void deleteTeacher(Integer[] id) {
        adminUserMapper.deleteTeacher(id);
    }

    @Override
    public LoginUser findStudentById(Integer id) {
        return adminUserMapper.findStudentById(id);
    }

    @Override
    public List<StudentInformation> findStudentByRoles() {
        return adminUserMapper.findStudentByRoles("stu");
    }

    @Override
    public void updateStudentUser(LoginUser loginUser) {
        adminUserMapper.updateStudentInformation(loginUser);
    }
}
