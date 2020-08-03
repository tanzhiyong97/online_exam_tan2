package com.tan.service.Impl;

import com.tan.mapper.StudentScoreMapper;
import com.tan.pojo.Score;
import com.tan.pojo.StudentInformation;
import com.tan.service.StudentScoreService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class StudentScoreServiceImpl implements StudentScoreService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Override
    public boolean addStuExamScore(Score score) {
        int a=0;
        a = studentScoreMapper.addStuExamScore(score);
        if(a==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public StudentInformation findExamStuByUsername(String username) {
        return studentScoreMapper.findExamStuByUsername(username);
    }

    @Override
    public List<Map<String, Object>> findExamByUsername(String username) {
        return studentScoreMapper.findExamScoreByUsername(username);
    }

    @Override
    public void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }
}
