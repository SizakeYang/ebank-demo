package com.sizake.ebank.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AddressMapper {

    Map<String, String> getAll();
}
