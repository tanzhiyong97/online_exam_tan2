package com.tan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
    private Integer id;
    private String content;
    private String type;
    private String subjectName;
    private Integer testId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
