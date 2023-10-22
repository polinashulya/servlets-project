package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User getById(long id);

    void save(User user);

    void delete(long id); // todo should be soft (not truly delete, just deactivate)

}
