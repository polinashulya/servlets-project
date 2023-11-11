package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.UserRepository;
import com.example.repository.impl.UserRepositoryImpl;
import com.example.service.UserService;
import com.example.validator.UserValidator;
import com.example.validator.UserValidatorImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValidator validator;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
        this.validator = new UserValidatorImpl();
    }

    @Override
    public User getById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return userRepository.findAll(sortBy, sortType, countryId, search, page, pageSize);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(User user) {
        if (!validator.validate(user.getLogin(), user.getPassword(),
                user.getFirstname(), user.getSurname(), user.getBirthDate())) {
            throw new ServiceException("Information is not valid!");
        }

        userRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new ServiceException("Login is already in use!"));

        try {
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

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return userRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
