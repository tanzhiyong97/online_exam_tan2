package com.tan.controller.student;

import com.tan.pojo.Questions;
import com.tan.pojo.Score;
import com.tan.pojo.StudentInformation;
import com.tan.service.ExamQuestionService;
import com.tan.service.ExamSubjectService;
import com.tan.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/stu")
public class StudentScoreController {
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private ExamSubjectService examSubjectService;
    @Autowired
    private StudentScoreService studentScoreService;

    /**
     * 计算学生考试成绩并存储到数据库
     * 将表单插传输的name和value以map形式接受它然后遍历它
     */
    @RequestMapping("/postExam")
    public String postExam(@RequestParam Map<String, String> map, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        //testId代表套题的id
        Integer testId = null;
        Questions questions = new Questions();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        Integer radioScores = 0; //单选分
        Integer checkScores = 0; //多选分
        Integer total = 0; //总得分
        while(it.hasNext()){
            String key = it.next();
            String value = map.get(key);
            questions = examQuestionService.findExamAnswerById(key);
            testId = questions.getTestId();
            if(questions.getType().equals("单选")){
                if(questions.getAnswer().equals(value))
                    radioScores+=10;
            }
            if(questions.getType().equals("多选")){
                if(questions.getAnswer().equals(value))
                    checkScores+=20;
            }
        }

        total=radioScores+checkScores;
        //存储数据到数据库
        Score score = new Score();
        score.setRadioScores(radioScores);
        score.setCheckScores(checkScores);
        score.setTotal(total);
        score.setCreateTime(new Timestamp(new Date().getTime()));
        score.setTestId(testId);
        score.setUsername(username);
        boolean addStuExamScore = studentScoreService.addStuExamScore(score);

        //返回参数给前端显示
        StudentInformation stuInformation = studentScoreService.findExamStuByUsername(username);
        Integer stuId = stuInformation.getStuId();
        String stuName = stuInformation.getStuName();
        String stuClass = stuInformation.getStuClass();
        System.out.println(stuClass);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("radioScores", radioScores);
        resultMap.put("checkScores", checkScores);
        resultMap.put("total", total);
        resultMap.put("stuClass", stuClass);
        resultMap.put("stuName", stuName);
        resultMap.put("stuId", stuId);
        model.addAttribute("resultMap", resultMap);
        return "student/examResult";
    }

    //用户查询所有考试记录
    @RequestMapping("/queryResult")
    public String queryResult(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");

        //返回用户查询所有考试记录
        List<Map<String, Object>> queryList = studentScoreService.findExamByUsername(username);
        System.out.println(queryList);
        model.addAttribute("queryList", queryList);
        return "student/examResultAll";
    }
}
