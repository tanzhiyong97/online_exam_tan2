package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInformation {
    private Integer teacherId;
    private String username;
    private String password;
    private String className;
    private String teacherName;
    private String tel;
}
