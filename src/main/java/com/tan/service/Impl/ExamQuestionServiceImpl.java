package com.tan.service.Impl;

import com.tan.mapper.ExamQuestionMapper;
import com.tan.pojo.ExamSubject;
import com.tan.pojo.Questions;
import com.tan.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private ExamQuestionMapper examQuestionMapper;

    @Override
    public List<Questions> examQuestionByTeacherName(String teacherName) {
        return examQuestionMapper.examQuestionByTeacherName(teacherName);
    }

    @Override
    public List<ExamSubject> findExamSubjectByTeacherName(String teacherName) {
        return examQuestionMapper.findExamSubjectByTeacherName(teacherName);
    }

    @Override
    public Questions findExamQuestionById(Integer id) {
        return examQuestionMapper.findExamQuestionById(id);
    }

    @Override
    public void saveExamQuestions(Questions questions) {
        //从前端穿回来的数据进行判断
        // 如果传回来的数据没有id，证明是新增
        if(questions.getId()==null){
            examQuestionMapper.addExamQuestions(questions);
        }else {
            examQuestionMapper.updateExamQuestions(questions);
        }
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        return examQuestionMapper.deleteQuestion(id);
    }

    @Override
    public List<Map<String, Object>> findExamRadioQuestions(Integer testId) {
        return examQuestionMapper.findExamRadioQuestions(testId);
    }

    @Override
    public List<Map<String, Object>> findExamCheckboxQuestions(Integer testId) {
        return examQuestionMapper.findExamCheckboxQuestions(testId);
    }

    @Override
    public Questions findExamAnswerById(String id) {
        return examQuestionMapper.findExamAnswerById(id);
    }
}
