package repository;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import entity.UserStatus;

import java.time.LocalDate;
import java.time.Month;
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
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.delete(id);
    }

    public static void main(String[] args) {

        UserRepositoryImpl repository = new UserRepositoryImpl();
        List<User> users = repository.findAll();

//        User user = new User(3L, "11111", "pass", "chakun", "polya", UserStatus.ADMIN, LocalDate.of(2014, Month.JANUARY, 1), false);
//        repository.save(user);

        System.out.println(
                users
        );

//        repository.deleteById(6);

//        System.out.println(
//                repository.getById(1)
//        );

    }
}
