package com.example.service;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface UserService {

    User getById(String userId);

    List<User> getAll(String sortBy, String sortType);

    void add(User user);

    void deleteById(Long userId);

}
