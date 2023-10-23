package dao;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();

    User getById(long id);

    Optional<User> findById(long id);

    void  save(User user);

    void delete(long id); // todo should be soft (not truly delete, just deactivate)

}
