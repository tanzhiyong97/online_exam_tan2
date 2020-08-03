package com.tan.mapper;

import com.tan.pojo.ExamSubject;
import com.tan.pojo.Questions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ExamQuestionMapper {
    //对应教师名下的试题
    public List<Questions> examQuestionByTeacherName(String teacherName);

    //对应教师名下的课程
    public List<ExamSubject> findExamSubjectByTeacherName(String teacherName);

    //通过id查找试题
    public Questions findExamQuestionById(Integer id);

    //增加试题
    public void addExamQuestions(Questions questions);

    //修改试题
    public void updateExamQuestions(Questions questions);

    //删除试题
    public boolean deleteQuestion(Integer id);

    //返回单选题目
    public List<Map<String, Object>> findExamRadioQuestions(Integer testId);

    //返回多选题目
    public List<Map<String, Object>> findExamCheckboxQuestions(Integer testId);

    //通过id查找试题答案
    public Questions findExamAnswerById(String id);

}
