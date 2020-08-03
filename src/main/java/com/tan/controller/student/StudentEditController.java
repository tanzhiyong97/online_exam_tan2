package com.tan.controller.student;


import com.tan.pojo.LoginUser;
import com.tan.pojo.StudentInformation;
import com.tan.service.StuUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/stu")
public class StudentEditController {
    @Autowired
    private StuUserService stuUserService;

    @RequestMapping("/toChangePassword")
    public String toChangePassword(){
        return "student/changePassword";
    }

    @RequestMapping("/toChangeInformation")
    public String toChangeInformation(){
        return "student/changeInformation";
    }

    //修改密码
    //先加密后认证原密码，在加密新密码进行修改
    @RequestMapping("/changePassword")
    public String changePassword(String password1, String password2, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        Md5Hash md5Password = new Md5Hash(password1, username, 5);
        System.out.println(md5Password.toString());
        LoginUser user1 = stuUserService.findByUsername(username);
        LoginUser user2 = new LoginUser();
        //验证原密码是否正确
        if(user1.getPassword().equals(md5Password.toString())){
            Md5Hash md5Password2 = new Md5Hash(password2, username, 5);
            user2.setUsername(username);
            user2.setPassword(md5Password2.toString());
            stuUserService.updatePassword(user2);
            model.addAttribute("msg", "修改成功");
        }else{
            model.addAttribute("msg", "原密码错误");
        }
        return "student/changePassword";
    }

    //修改用户信息
    @RequestMapping("/changeStuInformation")
    public String ChangeInformation(HttpSession session, Model model, String stuName,
                                    String stuId, String stuTime, String sex,
                                    String stuClass, String tel) throws ParseException {
        String username = (String) session.getAttribute("username");
        //这里运行的时候会报错，但是不影响
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stuTime);
        StudentInformation student = new StudentInformation();
        student.setUsername(username);
        student.setSex(sex);
        student.setStuId(Integer.parseInt(stuId));
        student.setStuTime(date);
        student.setStuName(stuName);
        student.setStuClass(stuClass);
        student.setTel(tel);
        boolean changeInformation = stuUserService.updateInformation(student);
        if(changeInformation){
            model.addAttribute("msg","修改成功");
        }else{
            model.addAttribute("msg", "修改失败");
        }
        return "student/changeInformation";
    }

    //表单回显
    @RequestMapping("/back")
    @ResponseBody
    public StudentInformation back(HttpSession session){
        String username = (String) session.getAttribute("username");
        StudentInformation student = stuUserService.findInformationByUsername(username);
        return student;
    }
}
