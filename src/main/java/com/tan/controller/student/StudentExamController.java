package com.tan.controller.student;

import com.tan.pojo.ExamSubject;
import com.tan.service.ExamQuestionService;
import com.tan.service.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("/stu")
@Controller
public class StudentExamController {
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private ExamSubjectService examSubjectService;

    //跳转到在线考试页面
    @RequestMapping("/toExam")
    public String toExam(){
        return "student/exam";
    }

    //返回考试科目选择，返回数据为json数据
    @RequestMapping("/ExamSubjectList")
    @ResponseBody//用于转换对象为json
    public List<ExamSubject> list(HttpSession session){
        String username = (String) session.getAttribute("username");
        List<ExamSubject> list = examSubjectService.findAllExamSubject(username);
        return list;
    }

    //返回考试题目
    @RequestMapping("/toExamPageList")
    public String toExamPageList(@RequestParam(value = "subjectName") Integer testId, Model model, HttpSession session){
        //存储选择的考试课程的id号
        session.setAttribute("testId", testId);
        ExamSubject examSubject = examSubjectService.findExamSubjectByTestId(testId);
        model.addAttribute("timeLimit", examSubject.getTimeLimit());
        //返回单选题目
        List<Map<String, Object>> list1 = examQuestionService.findExamRadioQuestions(testId);
        model.addAttribute("radioQuestionsList", list1);
        //返回多选题目
        List<Map<String, Object>> list2 = examQuestionService.findExamCheckboxQuestions(testId);
        model.addAttribute("checkboxQuestionsList", list2);
        return "student/examPage";
    }

    //查询学生所有考试信息
    @RequestMapping("/queryExam")
    public String queryResult(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");

        //返回用户查询所有考试记录
        List<Map<String, Object>> queryList = examSubjectService.findAllExamByUsername(username);
        System.out.println(queryList);
        model.addAttribute("queryList", queryList);
        return "student/examInfo";
    }
}
