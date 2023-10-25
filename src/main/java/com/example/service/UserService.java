package com.example.service;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface UserService {

    void add(String login, String password, String confirmedPassword,
             String name, String surname, Date birthDate,
             String email, String phoneNumber);

    User getById(String userId);

    List<User> getAll() throws DAOException, ServiceException;

}
