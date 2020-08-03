package com.tan.controller.admin;

import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.pojo.TeacherInformation;
import com.tan.service.AdminUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminIndexController {
    @Autowired
    private AdminUserService adminUserService;

    //设计Map聚合存储需要给页面的对象数据
    private Map<String, Object> result = new HashMap<String, Object>();

    //管理员界面
    @RequestMapping("/toManageAdmin")
    public String managerAdmin(){
        return "admin/manageAdmin";
    }

    //管理员管理教师用户页面
    @RequestMapping("/toMangeTeacher")
    public String toManageTeacher(){
        return "admin/manageTeacher";
    }

    //管理员管理学生用户页面
    @RequestMapping("/toManageStudent")
    public String toManagerStudent(){
        return "admin/manageStudent";
    }

    //传输所有管理员用户信息
    @RequestMapping("/manageAdmin")
    @ResponseBody //用于转换对象为json
    public List<LoginUser> adminList(){
        List<LoginUser> list = adminUserService.findAdminByRoles();
        return list;
    }
    //传输所有教师用户信息
    @RequestMapping("/manageTeacher")
    @ResponseBody //用于转换对象为json
    public List<TeacherInformation> teacherList(){
        List<TeacherInformation> list = adminUserService.findTeacherByRoles();
        return list;
    }
    //传输所有学生用户信息
    @RequestMapping("/manageStudent")
    @ResponseBody
    public List<StudentInformation> studentList(){
        List<StudentInformation> list = adminUserService.findStudentByRoles();
        return list;
    }

    //删除LoginUser数据
    @RequestMapping("/deleteLoginUser")
    @ResponseBody
    public Map<String,Object> deleteLoginUser(Integer[] id){
        try{
            adminUserService.deleteLoginUser(id);
            result.put("success", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    //删除TeacherInformation数据
/*    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public Map<String,Object> deleteTeacher(Integer[] id){
        try{
            System.out.println(id);

            adminUserService.deleteTeacher(id);
            result.put("success", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }*/

    //根据id查询对象，用于编辑对象时的表单回显
    @RequestMapping("/adminFindById")
    @ResponseBody
    public LoginUser adminFindById(Integer id){
        LoginUser loginUser = adminUserService.findAdminById(id);
        return loginUser;
    }
    //根据id查询对象，用于编辑对象时的表单回显
    @RequestMapping("/teacherFindById")
    @ResponseBody
    public TeacherInformation teacherFindById(Integer id){
        TeacherInformation teacher = adminUserService.findTeacherById(id);
        return teacher;
    }

    //根据username查询对象，用于编辑对象时的表单回显
    @RequestMapping("/teacherFindByUsername")
    @ResponseBody
    public TeacherInformation teacherFindByUsername(String username){
        TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
        return teacher;
    }

    //根据id查询对象，用于编辑对象时的表单回显
    @RequestMapping("/studentFindById")
    public LoginUser studentFindById(Integer id){
        LoginUser loginUser = adminUserService.findStudentById(id);
        return loginUser;
    }

    //保存数据，包括新增管理员信息和修改管理员信息
    @RequestMapping("/saveAdmin")
    @ResponseBody
    public Map<String, Object> saveAdmin(LoginUser loginUser){
        try {
            loginUser.setRoles("admin");
            System.out.println(loginUser.getPassword());
            Md5Hash md5 = new Md5Hash(loginUser.getPassword(), loginUser.getUsername(), 5);
            System.out.println(md5.toString());
            loginUser.setPassword(md5.toString());
            adminUserService.saveAdmin(loginUser);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    //保存数据，包括新增教师信息和修改教师信息
    @RequestMapping("/saveTeacher")
    @ResponseBody
    public Map<String, Object> saveTeacher(TeacherInformation teacherInformation){
        try{
            Md5Hash md5 = new Md5Hash(teacherInformation.getPassword(),teacherInformation.getTeacherName(),5);
            System.out.println(md5.toString());
            teacherInformation.setPassword(md5.toString());
            //新增教师用户分为两步，第一步在teacherInformation表格里添加，第二步在loginUser表格里添加，其中loginUser表里的有密码

            //第一步：在teacherInformation表里添加
            adminUserService.saveTeacher(teacherInformation);

            //第二步：在loginUser表里添加
            if(teacherInformation.getTeacherId()==null){
                LoginUser loginUser = new LoginUser();
                loginUser.setRoles("teacher");
                loginUser.setPassword(teacherInformation.getPassword());
                loginUser.setUsername(teacherInformation.getUsername());
                adminUserService.saveAdmin(loginUser);
            }else{
                LoginUser loginUser = new LoginUser();
                loginUser.setPassword(teacherInformation.getPassword());
                loginUser.setUsername(teacherInformation.getUsername());
                adminUserService.saveAdmin(loginUser);
            }
            result.put("success",true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    @RequestMapping("/saveStudent")
    @ResponseBody
    public Map<String, Object> saveStudent(LoginUser loginUser){
        try {
            Md5Hash md5 = new Md5Hash(loginUser.getPassword(), loginUser.getUsername(), 5);
            System.out.println(md5.toString());
            adminUserService.updateStudentUser(loginUser);
            result.put("success", true);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }




    @RequestMapping("/toError")
    public String errorPage(){
        return "error";
    }

}
