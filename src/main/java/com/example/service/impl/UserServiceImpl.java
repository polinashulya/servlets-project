package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.UserRepository;
import com.example.repository.impl.UserRepositoryImpl;
import com.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User getById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll(String sortBy, String sortType, String countryId) {
        try {
            return userRepository.findAll(sortBy, sortType, countryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(User user) {
        try {
            // валидация епта
            userRepository.save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
