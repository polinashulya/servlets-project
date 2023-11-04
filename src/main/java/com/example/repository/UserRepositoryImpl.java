package com.example.repository;

import com.example.dao.impl.UserDaoImpl;
import com.example.entity.User;
import com.example.exception.RepositoryException;
import com.example.dao.UserDao;
import com.example.exception.DAOException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAll(String sortBy, String sortType) {
        try {
            final String sql = userDao.getSortingSql(sortBy, sortType);
            return userDao.findAll(sql);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User getById(long id) {
        try {
            return userDao.getById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            userDao.save(user);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteById(long id) {
        try {
            userDao.delete(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

}
