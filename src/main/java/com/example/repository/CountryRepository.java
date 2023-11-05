package com.example.repository;

import com.example.entity.Country;

import java.util.List;

public interface CountryRepository {

    List<Country> findAll();
}
