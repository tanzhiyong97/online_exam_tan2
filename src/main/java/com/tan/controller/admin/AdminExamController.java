package com.tan.controller.admin;

import com.tan.pojo.ExamSubject;
import com.tan.service.ExamSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "管理员考试控制类")
@Controller
public class AdminExamController {
    @Autowired
    private ExamSubjectService examSubjectService;

    @ApiOperation("返回所有考试课程")
    //管理员查看所有考试课程页面
    @RequestMapping("/toManageExam")
    public String examShowForAdmin(){
        return "admin/manageExam";
    }

    @ApiOperation("返回所有管理员信息")
    //传输所有管理员用户信息
    @RequestMapping("/findExamForAdmin")
    @ResponseBody
    public List<ExamSubject> findExamForAdmin(){
        List<ExamSubject> list = examSubjectService.findExamSubjectForAdmin();
        System.out.println(list.toString());
        return list;
    }
}
