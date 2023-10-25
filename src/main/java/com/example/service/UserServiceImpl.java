package com.example.service;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.UserRepository;
import com.example.repository.UserRepositoryImpl;

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {

        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void add(String login, String password, String confirmedPassword, String firstname, String surname, Date birthDate, String email, String phoneNumber) {
    User user= User.builder()
            .login(login)
            .password(password)
            .firstName(firstname)
            .secondName(surname)
//            .login(login)
//            .login(login)
//            .login(login)
//            .login(login)
            .build();
        try {
             userRepository.save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll()  {
        try {
            return userRepository.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}