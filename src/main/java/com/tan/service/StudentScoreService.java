package com.tan.service;

import com.tan.pojo.Score;
import com.tan.pojo.StudentInformation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public interface StudentScoreService {

    //添加学生考试成绩记录
    public boolean addStuExamScore(Score score);

    //根据用户名查询学生信息
    public StudentInformation findExamStuByUsername(String username);

    //返回用户考试所有记录
    public List<Map<String, Object>> findExamByUsername(String username);

    //保存考试信息到Excel文档
    void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception;
}
