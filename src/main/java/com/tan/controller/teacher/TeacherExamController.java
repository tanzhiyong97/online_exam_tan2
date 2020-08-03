package com.tan.controller.teacher;

import com.tan.pojo.ExamResult;
import com.tan.pojo.ExamSubject;
import com.tan.pojo.Questions;
import com.tan.pojo.TeacherInformation;
import com.tan.service.AdminUserService;
import com.tan.service.ExamQuestionService;
import com.tan.service.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherExamController {
    @Autowired
    private ExamSubjectService examSubjectService;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private AdminUserService adminUserService;

    //设计Map聚合存储需要给页面的对象数据
    private Map<String, Object> result = new HashMap<String, Object>();

    //学生考试成绩查询页面
    @RequestMapping("/teaResultShow")
    public String ResultShow(){
        return "teacher/ResultShow";
    }

    //传输所有自己课程考试学生的成绩信息
    @RequestMapping("/manageStuExam")
    @ResponseBody
    public List<ExamResult> manageStuExam(HttpSession session){
        String username = (String) session.getAttribute("TeaUsername");
        Integer teacherId = examSubjectService.findTeacherIdByUsername(username);
        List<ExamResult> list = examSubjectService.manageStuExam(teacherId);
        return list;
    }


    //考试科目页面
    @RequestMapping("/toManageExamSubject")
    public String toManageExamSubject(){
        return "teacher/ExamLesson";
    }

    //考试题目页面
    @RequestMapping("/toManageQuestions")
    public String toManageQuestions(){
        return "teacher/ExamQuestion";
    }

    //查询该教师名下的考试科目信息
    @RequestMapping("/teaExamLesson")
    @ResponseBody
    public List<ExamSubject> teaExamLesson(HttpSession session){
        String username = (String) session.getAttribute("TeaUsername");
        TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
        List<ExamSubject> list = examSubjectService.findExamSubjectForTeacher(teacher.getTeacherName());
        return list;
    }

    //查询该教师名下的考试题目信息
    @RequestMapping("/ExamQuestion")
    @ResponseBody
    public List<Questions> findQuestionsByTeacherName(HttpSession session){
        String username = (String) session.getAttribute("TeaUsername");
        TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
        List<Questions> list = examQuestionService.examQuestionByTeacherName(teacher.getTeacherName());
        return list;
    }

    @RequestMapping("/TeacherExamSubjectList")
    @ResponseBody
    //返回考试科目，返回数据为json数据
    public List<ExamSubject> list(HttpSession session){
        String username = (String) session.getAttribute("TeaUsername");
        TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
        List<ExamSubject> list = examQuestionService.findExamSubjectByTeacherName(teacher.getTeacherName());
        return list;
    }

    //增加考试课程
    @RequestMapping("/saveExamSubject")
    @ResponseBody
    public Map<String, Object> saveExamSubject(HttpSession session, ExamSubject examSubject){
        try{
            String username = (String) session.getAttribute("TeaUsername");
            TeacherInformation teacher = adminUserService.findTeacherByUsername(username);
            System.out.println(teacher);
            examSubject.setTeacherName(teacher.getTeacherName());
            examSubject.setCreateTime(new Timestamp(new Date().getTime()));
            System.out.println(examSubject);
            examSubjectService.addExamSubject(examSubject);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/saveExamQuestions")
    @ResponseBody
    public Map<String, Object> saveExamQuestions(Questions questions){
        System.out.println(questions);
        try{
//            examQuestionService.findExamSubjectByTeacherName()
            examQuestionService.saveExamQuestions(questions);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }


    @RequestMapping("/findExamQuestionsById")
    @ResponseBody
    public Questions findExamQuestionsById(Integer id){
        Questions questions = examQuestionService.findExamQuestionById(id);
        return questions;
    }

    @RequestMapping("/deleteQuestion")
    @ResponseBody
    public Map<String, Object> deleteQuestion(Integer id){
        try{
            examQuestionService.deleteQuestion(id);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false );
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
