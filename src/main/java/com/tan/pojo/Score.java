package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Integer id;
    private Integer testId;
    private String username;
    private String subjectName;
    private Integer radioScores;
    private Integer checkScores;
    private Integer total;
    private Timestamp createTime;
    private String stuId;
    private String stuName;
}
