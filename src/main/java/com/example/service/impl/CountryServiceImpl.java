package com.example.service.impl;

import com.example.entity.Country;
import com.example.repository.CountryRepository;
import com.example.repository.impl.CountryRepositoryImpl;
import com.example.service.CountryService;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl() {
        countryRepository = new CountryRepositoryImpl();
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
