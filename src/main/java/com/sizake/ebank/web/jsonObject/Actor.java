package com.sizake.ebank.web.jsonObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    private String id;
    private String firstName;
    private String lastNameTest;
    private List<Film> films;




    //调整日期json格式-jackson
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_update;//由于<setting name="mapUnderscoreToCamelCase" value="true"/>,因而需要指定


    //通过@Arg-name属性指定,没有顺序要求
    public Actor(@Param("last_update") Date last_update, @Param("id") String id) {
        this.id = id;
        this.last_update = last_update;
    }

    public Actor(String id, Date last_update) {
        this.id = id;
        this.last_update = last_update;
    }
}
