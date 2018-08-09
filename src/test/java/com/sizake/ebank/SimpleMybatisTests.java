package com.sizake.ebank;

import com.sizake.ebank.db.dao.CityMapper;
import com.sizake.ebank.db.dao.LanguageMapper;
import com.sizake.ebank.web.jsonObject.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMybatisTests {

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private CityMapper cityMapper;



    @Test
    public void annontiation() {
        System.out.println(String.format("list is %s ",this.languageMapper.findByStringAndReturnList("ABW")));

        Map<String,Object> conditions = new HashMap<>();
        conditions.put("country","ABW");
        conditions.put("language","Dutch");
        System.out.println(String.format("language-map is %s ",this.languageMapper.findByMapAndReturnMap(conditions)));
        System.out.println(String.format("language-list map is %s ",this.languageMapper.findByMapAndReturnListMap(conditions)));


        Language language = new Language();
        language.setCountryCode("ABW");
        language.setLanguage("Dutch");

        System.out.println(String.format("language-obj is %s ",this.languageMapper.findByObjAndReturnObj(language)));

        System.out.println(String.format("city-multiMap is %s ",this.cityMapper.getAll()));

    }
}
