package com.tan.mapper;

import com.tan.pojo.Score;
import com.tan.pojo.StudentInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StudentScoreMapper {
    //添加学生考试成绩记录
    public int addStuExamScore(Score score);
    //通过用户名查找考试学生信息
    public StudentInformation findExamStuByUsername(String username);
    //返回用户考试所有记录
    public List<Map<String, Object>> findExamScoreByUsername(String username);

}
