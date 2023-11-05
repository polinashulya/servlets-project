package com.example.repository.impl;

import com.example.dao.CountryDao;
import com.example.dao.impl.CountryDaoImpl;
import com.example.entity.Country;
import com.example.repository.CountryRepository;

import java.util.List;

public class CountryRepositoryImpl implements CountryRepository {

    private final CountryDao countryDao;

    public CountryRepositoryImpl() {
        countryDao = new CountryDaoImpl();
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }
}
