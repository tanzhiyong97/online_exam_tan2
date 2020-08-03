package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInformation {

    private Integer id;
    private String username;
    private String password;
    private Integer stuId;
    private String stuName;
    private Date stuTime;
    private String sex;
    private String stuClass;
    private String tel;
}
