package com.tan.controller.admin;

import com.tan.pojo.LoginUser;
import com.tan.pojo.TeacherInformation;
import com.tan.service.AdminUserService;
import com.tan.service.StuUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private StuUserService stuUserService;

    //教师和管理员登录页面
    @RequestMapping("/alogin")
    public String toLogin(){
        return "admin/adminLogin";
    }
    //使用教师用户登录后的页面
    @RequestMapping("/teaIndex")
    public String teaIndex(){
        return "teacher/teaIndex";
    }
    //使用管理员用户登录后的页面
    @RequestMapping("/adminIndex")
    public String adminIndex(){
        return "admin/adminIndex";
    }

    @RequestMapping("/adminLoginSubmit")
    public String login(String username, String password, Model model, HttpSession session){
        /**
         * 使用Shiro编写认证操作
        */
        //判断用户名是否存在
        if(!stuUserService.findRegisterUsername(username)){
            model.addAttribute("msg", "用户名不存在");
            return "admin/adminLogin";
        }

        LoginUser user = adminUserService.findByUsername(username);
        if(!(user.getRoles().equals("teacher")||user.getRoles().equals("admin"))){
            model.addAttribute("msg", "登录失败");
            return "admin/adminLogin";
        }

        //1. 获取Subject
        Subject subject = SecurityUtils.getSubject();
        //进行MD5盐值加密，第一个参数为密码，第二个参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5password = new Md5Hash(password, username, 5);
        System.out.println(md5password.toString());

        //2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,md5password.toString());

        //3. 执行登录方法
        try{
            //验证密码
            subject.login(token);

            //登录成功
            //根据用户权限，跳转到对应的页面
            if(user.getRoles().equals("teacher")){
                TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
                session.setAttribute("teacherName", teacher.getTeacherName());
                session.setAttribute("role", "Teacher");
                session.setAttribute("TeaUsername", username);
                return "redirect:/teaIndex";
            }
            if(user.getRoles().equals("admin")){
                session.setAttribute("role", "Admin");
                session.setAttribute("username", username);
                return "redirect:/adminIndex";
            }
            return "admin/adminLogin";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "admin/adminLogin";
        }catch (IncorrectCredentialsException e){
            //登录失败，密码错误
            model.addAttribute("msg", "密码错误");
            return "admin/adminLogin";
        }
    }


}
