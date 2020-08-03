package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSubject {
    private Integer testId;
    private String subjectName;
    private Timestamp createTime;
    private String teacherName;
    private Integer timeLimit;
//    private TeacherInformation teacher;
}
