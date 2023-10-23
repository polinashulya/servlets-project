package repository;

import dao.UserDao;
import entity.User;

import java.util.List;

public interface UserRepository  {

    List<User> findAll();

    User getById(long id);

     void save(User user);

     void deleteById(long id);

}
