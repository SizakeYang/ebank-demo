package com.sizake.ebank.web.jsonObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class ComplexObject {


    private String s1;

    private Integer i1;

    private BigDecimal b1;

    private Date d1;

    private String[] a1;

    private List<String> ls1;

    private Map<String, String> mss1;

    private SimpleJson[] a01;

    private List<SimpleJson> lo1;

    private Map<String, List<SimpleJson>> mslo1;

    private List<Map<String, String>> lmss1;

    private List<Map<String, List<SimpleJson>>> lmslo1;


}
