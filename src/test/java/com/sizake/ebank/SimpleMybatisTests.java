package com.sizake.ebank;

import com.sizake.ebank.db.dao.CityMapper;
import com.sizake.ebank.db.dao.LanguageMapper;
import com.sizake.ebank.web.jsonObject.Language;
import com.sizake.ebank.web.jsonObject.Languages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//
//        final Map<String, Object> conditions = new HashMap<>();
//        //conditions.put("country", "ABW");
//        //conditions.put("language", "Dutch");
//        conditions.put("title", "");
//        //conditions.put("countryCode", "A");

//        System.out.println(String.format("language-map is %s ", this.languageMapper.findByMapAndReturnMap(conditions)));
//        final List<Map<String, String>> language_list1 = this.languageMapper.findByMapAndReturnListMap1(conditions);
//        System.out.println(String.format("language-list map1 is %s ", language_list1));
//        System.out.println(String.format("language-list map1's size is %s ", language_list1.size()));

//        final List<Map<String, String>> language_list2 = this.languageMapper.findByScript(conditions);
//
//        System.out.println(String.format("language-list map2 is %s ", language_list2));
//        System.out.println(String.format("language-list map2's size is %s ", language_list2.size()));
//
//        // 针对 ReuseExecutor 测试
//        final List<Map<String, String>> language_list3 = this.languageMapper.findByScript(conditions);
//        System.out.println(String.format("language-list map3 is %s ", language_list3));
//        System.out.println(String.format("language-list map3's size is %s ", language_list3.size()));

//        final Language language = new Language();
//        language.setCountryCode("ABW");
//        language.setLanguage("Dutch");
//        System.out.println(String.format("language-obj is %s ", this.languageMapper.findByObjAndReturnObj(language)));


//        final Map<String, City> city_map1 = this.cityMapper.getAllAndReturnMapObj();
//        System.out.println(String.format("city-multiMap is %s ", city_map1));
//
//        Map<String, Map<String, String>> city_map2 = this.cityMapper.getAllAndReturnListMap();
//        System.out.println(String.format("city-multiListMap is %s ",city_map2));
//

//        final List<Map<String, String>> language_list4 = this.languageMapper.findWithLike(conditions);
//        System.out.println(String.format("language-list map4 is %s ", language_list4));
//        System.out.println(String.format("language-list map4's size is %s ", language_list4.size()));

//        final List<Map<String, String>> language_list5 = this.languageMapper.findByScriptWithChooseAndPre(conditions);
//        System.out.println(String.format("language-list map5 is %s ", language_list5));
//        System.out.println(String.format("language-list map5's size is %s ", language_list5.size()));

//        final List<String> item_list = new ArrayList<>();
//        item_list.add("ABW");
//        item_list.add("AFG");
//        //final List<Map<String, String>> language_list6 = this.languageMapper.findByScriptWithFor(item_list);
//        final List<Map<String, String>> language_list7 = this.cityMapper.findByScriptWithFor(item_list);
////        System.out.println(String.format("language-list map6 is %s ", language_list6));
////        System.out.println(String.format("language-list map6's size is %s ", language_list6.size()));
//
//        System.out.println(String.format("language-list map7 is %s ", language_list7));
//        System.out.println(String.format("language-list map7's size is %s ", language_list7.size()));

        final List<Language> item_list = new ArrayList<>();
        final Language l1 = new Language();
        l1.setCountryCode("ABW");
        item_list.add(l1);
        final Language l2 = new Language();
        l2.setCountryCode("AFG");
        item_list.add(l2);

        final Map<String, Object> obj = new HashMap<>();
        obj.put("countryCodes", item_list);


        Languages ls1 = new Languages(item_list);

//        final List<Map<String, String>> language_list8 = this.languageMapper.findByScriptWithFor1(item_list);
//        System.out.println(String.format("language-list map8 is %s ", language_list8));
//        System.out.println(String.format("language-list map8's size is %s ", language_list8.size()));

//        final List<Map<String, String>> language_list9 = this.languageMapper.findByScriptWithFor2(obj);
//        System.out.println(String.format("language-list map9 is %s ", language_list9));
//        System.out.println(String.format("language-list map9's size is %s ", language_list9.size()));

        final List<Map<String, String>> language_list10 = this.languageMapper.findByScriptWithFor3(ls1);
        System.out.println(String.format("language-list map10 is %s ", language_list10));
        System.out.println(String.format("language-list map10's size is %s ", language_list10.size()));

//        final Map<String, Object> conditions = new HashMap<>();
//        conditions.put("language", "Dutch");
//        conditions.put("countryCode", "ABW");
//        final List<Map<String, String>> language_list11 = this.languageMapper.findByScriptWithFor4(conditions);
//        System.out.println(String.format("language-list map11 is %s ", language_list11));
//        System.out.println(String.format("language-list map11's size is %s ", language_list11.size()));

    }
}
