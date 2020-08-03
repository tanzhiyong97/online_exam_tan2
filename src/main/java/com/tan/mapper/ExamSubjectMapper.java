package com.tan.mapper;

import com.tan.pojo.ExamResult;
import com.tan.pojo.ExamSubject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ExamSubjectMapper {
    //查询该学生所属教师名下所有考试科目
    public List<ExamSubject> findAllExamSubject(String username);

    //Admin查询考试科目及教师信息
    public List<ExamSubject> findExamSubjectForAdmin();

    //Teacher查询该教师名下考试科目及信息
    public List<ExamSubject> findExamSubjectForTeacher(@RequestParam(value = "teacherName") String teacherName);

    //增加考试课程
    public boolean addExamSubject(ExamSubject examSubject);

    //通过testId查找考试科目信息
    public ExamSubject findExamSubjectByTestId(Integer testId);

    //返回所有自己考试课程下学生的考试成绩
    public List<ExamResult> manageStuExam(Integer id);

    public Integer findTeacherIdByUsername(String username);

    //查询该学生名下全部考试信息
    public List<Map<String, Object>> findAllExamByUsername(String username);

}
