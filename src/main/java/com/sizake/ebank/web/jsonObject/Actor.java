package com.sizake.ebank.web.jsonObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class Actor {
    private String id;
    private String firstName;
    private String lastName;

    //调整日期json格式-jackson
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_update;
}
