package com.sizake.ebank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sizake.ebank.db.dao.AddressMapper;
import com.sizake.ebank.db.dao.CityMapper;
import com.sizake.ebank.db.dao.LanguageMapper;
import com.sizake.ebank.db.dao.SakilaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMybatisTests {

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private SakilaMapper sakilaMapper;

    @Autowired
    private AddressMapper addressMapper;


    @Test
    public void annontiation() throws JsonProcessingException {
        Map<String, String> map = this.addressMapper.getAll();

        System.out.println(map);
    }
}
