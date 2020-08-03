package com.tan.controller.student;

import com.tan.pojo.LoginUser;
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
@RequestMapping("/stu")
public class StuLoginController {
    @Autowired
    private StuUserService stuUserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "student/login";
    }

    @RequestMapping("/index")
    public String stuLogin(){
        return "student/index";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/loginSubmit")
    public String login(String username, String password, Model model, HttpSession session){

        /**
         * 使用shiro编写认证操作
         */
        //判断用户名是否存在
        if(!stuUserService.findRegisterUsername(username)){
            model.addAttribute("msg", "用户名不存在");
            return "student/login";
        }
        //判断是否是学生用户
        LoginUser user = stuUserService.findByUsername(username);
        if(!user.getRoles().equals("stu")){
            model.addAttribute("msg","登录失败");
            return "redirect:/stu/toLogin";
        }

        //进行登录验证
        //1. 获取subject
        Subject subject = SecurityUtils.getSubject();
        //进行Md5加密，第一个参数为密码，第二个参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5Hash = new Md5Hash(password, username, 5);
        System.out.println(md5Hash.toString());

        //2. 封装用户数据（参数： 用户名+密码）
        UsernamePasswordToken token = new UsernamePasswordToken(username, md5Hash.toString());

        //3. 执行登录方法
        try{
            subject.login(token);
            session.setAttribute("username", username);
            //登录成功
            //跳转到对应的学生界面
            return "redirect:/stu/index";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "student/login";
        }catch(IncorrectCredentialsException e){
            //登录失败，密码错误
            model.addAttribute("msg", "密码错误");
            return "student/login";
        }
    }
}
