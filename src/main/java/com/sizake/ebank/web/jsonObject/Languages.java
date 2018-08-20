package com.sizake.ebank.web.jsonObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor
public class Languages {
    private List<Language> languages;
}
