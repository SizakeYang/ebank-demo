package com.sizake.ebank;

import com.sizake.ebank.db.dao.CityMapper;
import com.sizake.ebank.db.dao.LanguageMapper;
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
//        System.out.println(String.format("list is %s ", this.languageMapper.findByStringAndReturnList("ABW")));

        final Map<String, Object> conditions = new HashMap<>();
        conditions.put("country", "ABW");
        conditions.put("language", "Dutch");
//        System.out.println(String.format("language-map is %s ", this.languageMapper.findByMapAndReturnMap(conditions)));
//        final List<Map<String, String>> language_list1 = this.languageMapper.findByMapAndReturnListMap1(conditions);
//        System.out.println(String.format("language-list map1 is %s ", language_list1));
//        System.out.println(String.format("language-list map1's size is %s ", language_list1.size()));
//
//
//        final Language language = new Language();
//        language.setCountryCode("ABW");
//        language.setLanguage("Dutch");
//
//        System.out.println(String.format("language-obj is %s ", this.languageMapper.findByObjAndReturnObj(language)));
//


        Map<String,Map<String, String>> language_multi1 = this.languageMapper.findByMapAndReturnMultiMap1(conditions);
        System.out.println(String.format("language-multi1 is %s ",language_multi1));



//        Map<String, City> city_map1 = this.cityMapper.getAllAndReturnMapObj();
//        System.out.println(String.format("city-multiMap is %s ", city_map1));
//
//        Map<String, Map<String, String>> city_map2 = this.cityMapper.getAllAndReturnListMap();
//        System.out.println(String.format("city-multiListMap is %s ",city_map2));
//
//


    }
}
