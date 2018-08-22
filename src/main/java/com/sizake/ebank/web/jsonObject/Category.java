package com.sizake.ebank.web.jsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Alias("myCategory")//它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余
public class Category {
    private String id;
    private String name;
}
