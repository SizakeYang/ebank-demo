package com.sizake.ebank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sizake.ebank.db.dao.SakilaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//import com.sizake.ebank.db.dao.CityMapper;
//import com.sizake.ebank.db.dao.LanguageMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMybatisTests {

//    @Autowired
//    private LanguageMapper languageMapper;
//
//    @Autowired
//    private CityMapper cityMapper;

    @Autowired
    private SakilaMapper sakilaMapper;


    @Test
    public void annontiation() throws JsonProcessingException {
        final List list = this.sakilaMapper.getFilm4();
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }
}
