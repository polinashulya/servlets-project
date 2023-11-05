package com.example.dao;

import com.example.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> findAll();
}
