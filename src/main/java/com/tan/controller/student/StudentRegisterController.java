package com.tan.controller.student;

import com.alibaba.fastjson.JSON;
import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.service.StuUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/stu")
public class StudentRegisterController {
    @Autowired
    private StuUserService stuUserService;

    //跳转页面
    @RequestMapping("/toStudentRegister")
    public String register(){
        return "student/stuRegister";
    }

    @InitBinder
    //接受Date类型数据
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    //判断注册的用户名是否存在
    @RequestMapping("/findRegisterUsername")
    @ResponseBody
    public Map<String, Object>  findRegisterUsername(String username){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean isExist;
        if(stuUserService.findRegisterUsername(username)){
            isExist = true;
        }else{
            isExist = false;
        }
        resultMap.put("isExist", isExist);
        return resultMap;
    }

    @RequestMapping("/StudentRegister")
    //注册逻辑处理
    public String toRegister(String username, String password, Model model,
                             Integer stuId, String stuName, String sex, Date stuTime,
                             String stuClass, String tel){
        if(stuUserService.findRegisterUsername(username)){
            model.addAttribute("msg", "用户已经存在");
            return "student/stuRegister";
        }

        //进行Md5加密，第一个参数为密码，第二个参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5RegisterPassword = new Md5Hash(password, username, 5);
        System.out.println(md5RegisterPassword.toString());
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(username);
        loginUser.setRoles("stu");
        loginUser.setPassword(md5RegisterPassword.toString());
        //注册用户账号(users_login表）
        boolean addRegister = stuUserService.register(loginUser);

        //注册用户详细信息(student_information表）
        StudentInformation student = new StudentInformation();
        student.setUsername(username);
        student.setStuId(stuId);
        student.setPassword(md5RegisterPassword.toString());
        student.setStuName(stuName);
        student.setStuTime(stuTime);
        student.setSex(sex);
        student.setStuClass(stuClass);
        student.setTel(tel);
        boolean addStudentInformation = stuUserService.addStudentInformation(student);
        if(addRegister&&addStudentInformation){
            model.addAttribute("msg", "注册成功");
        }else{
            model.addAttribute("msg", "注册失败");
        }
        return "student/stuRegister";
    }
}
