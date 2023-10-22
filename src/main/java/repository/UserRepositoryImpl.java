package repository;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private UserDao userDao;

    public UserRepositoryImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void save(UserDao userDao) {

    }

    public static void main(String[] args) {

        UserRepositoryImpl repository = new UserRepositoryImpl();
        List<User> users = repository.findAll();

        System.out.println(

        );

        System.out.println(
                repository.getById(1)
        );

    }
}
