package com.tan.service.Impl;

import com.tan.mapper.ExamSubjectMapper;
import com.tan.pojo.ExamResult;
import com.tan.pojo.ExamSubject;
import com.tan.service.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamSubjectServiceImpl implements ExamSubjectService {
    @Autowired
    private ExamSubjectMapper examSubjectMapper;

    @Override
    public List<ExamSubject> findAllExamSubject(String username) {
        return examSubjectMapper.findAllExamSubject(username);
    }

    @Override
    public List<ExamSubject> findExamSubjectForAdmin() {
        return examSubjectMapper.findExamSubjectForAdmin();
    }

    @Override
    public List<ExamSubject> findExamSubjectForTeacher(String teacherName) {
        return examSubjectMapper.findExamSubjectForTeacher(teacherName);
    }

    @Override
    public boolean addExamSubject(ExamSubject examSubject) {
        return examSubjectMapper.addExamSubject(examSubject);
    }

    @Override
    public ExamSubject findExamSubjectByTestId(Integer testId) {
        return examSubjectMapper.findExamSubjectByTestId(testId);
    }

    @Override
    public List<ExamResult> manageStuExam(Integer id) {
        return examSubjectMapper.manageStuExam(id);
    }

    @Override
    public Integer findTeacherIdByUsername(String username) {
        return examSubjectMapper.findTeacherIdByUsername(username);
    }

    @Override
    public List<Map<String, Object>> findAllExamByUsername(String username) {
        return examSubjectMapper.findAllExamByUsername(username);
    }
}
