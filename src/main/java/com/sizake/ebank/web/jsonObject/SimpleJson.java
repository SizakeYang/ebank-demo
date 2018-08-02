package com.sizake.ebank.web.jsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class SimpleJson {

    @Size(min = 2, max = 10)
    private String name;

    @Range(min = 18, max = 30, groups = {Adult.class})
    private Integer age;



    public interface Adult {
    }

}
