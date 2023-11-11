package com.example.dao;

import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll(String sql, String page, String pageSize);

    User getById(long id);

    Optional<User> findById(long id);

    void save(User user);

    void delete(long id);

    User getByLogin(String login);

    Optional<User> findByLogin(String login);

    String getSql(String sortBy, String sortType, String countryId, String search);

    int getTotalResult(String sql);

}
