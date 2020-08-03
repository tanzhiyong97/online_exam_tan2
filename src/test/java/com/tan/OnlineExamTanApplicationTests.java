package com.tan;

import com.tan.mapper.ExamSubjectMapper;
import com.tan.pojo.ExamSubject;
import com.tan.pojo.Questions;
import com.tan.pojo.TeacherInformation;
import com.tan.service.AdminUserService;
import com.tan.service.ExamQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OnlineExamTanApplicationTests {
    @Autowired
    private ExamSubjectMapper examSubjectMapper;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private AdminUserService adminUserService;
    @Test
    void contextLoads() {
        List<Questions> list = examQuestionService.examQuestionByTeacherName("马1");
        for (Questions questions : list) {
            System.out.println(questions);
        }
    }

}
