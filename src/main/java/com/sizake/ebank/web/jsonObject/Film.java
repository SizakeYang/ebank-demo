package com.sizake.ebank.web.jsonObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Alias("myFilm")//它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余
public class Film {

    private String id;
    private String title;
    private String rentalDuration;
    private BigDecimal cost;
    private Category category;//一对一
    private List<Actor> actors;//一对多-->多对多

}
