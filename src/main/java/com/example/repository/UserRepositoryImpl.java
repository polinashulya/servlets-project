package com.example.repository;

import com.example.dao.impl.UserDaoImpl;
import com.example.entity.User;
import com.example.exception.RepositoryException;
import com.example.dao.UserDao;
import com.example.exception.DAOException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private UserDao userDao;

    public UserRepositoryImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.findAll();
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

    public static void main(String[] args) {

        UserRepositoryImpl repository = new UserRepositoryImpl();
        List<User> users = repository.findAll();

//        User user = new User(3L, "11111", "pass", "chakun", "polya", UserStatus.ADMIN, LocalDate.of(2014, Month.JANUARY, 1), false);
//        org.repository.save(user);

        System.out.println(
                users
        );

//        org.repository.deleteById(6);

//        System.out.println(
//                org.repository.getById(1)
//        );

    }
}
