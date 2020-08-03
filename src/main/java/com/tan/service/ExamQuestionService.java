package com.tan.service;

import com.tan.pojo.ExamSubject;
import com.tan.pojo.Questions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ExamQuestionService {
    //对应教师名下的试题
    public List<Questions> examQuestionByTeacherName(String teacherName);

    //对应教师名下的课程
    public List<ExamSubject> findExamSubjectByTeacherName(String teacherName);

    //通过id查找试题
    public Questions findExamQuestionById(Integer id);

    //保存增加或者修改考试试题
    public void saveExamQuestions(Questions questions);

    //删除试题
    public boolean deleteQuestion(Integer id);

    //返回单选题目
    public List<Map<String, Object>> findExamRadioQuestions(Integer testId);

    //返回多选题目
    public List<Map<String, Object>> findExamCheckboxQuestions(Integer testId);

    //通过id查找试题答案
    public Questions findExamAnswerById(String id);
}
