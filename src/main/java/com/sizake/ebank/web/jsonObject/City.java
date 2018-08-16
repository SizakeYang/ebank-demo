package com.sizake.ebank.web.jsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
@Alias("mysql_city")
public class City {
    private String id;
    private String name;
    private String countryCode;
    private String district;
    private Long population;
}
