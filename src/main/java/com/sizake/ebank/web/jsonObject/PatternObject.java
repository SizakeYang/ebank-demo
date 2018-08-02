package com.sizake.ebank.web.jsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class PatternObject {
    //@Pattern(regexp = "^[A-Za-z]+$")
    @Pattern(regexp = "^[A-Za-z0-9_/-]+$")
    private String str;


    //1 汉字：^[\u4e00-\u9fa5]{0,}$
    //2 英文和数字：^[A-Za-z0-9]+$
    //3 linux 路径 ^([\/][\w-]+)+$
}
