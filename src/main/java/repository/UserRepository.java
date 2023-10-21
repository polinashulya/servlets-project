package repository;

import entity.User;
import repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    @Override
    default User getEntityById(String id) {
        return null;
    }

    @Override
    default boolean removeById(String id) {
        return false;
    }

    @Override
    default boolean add(User user) {
        return false;
    }

    @Override
    default boolean update(User user) {
        return false;
    }

    @Override
    default List<User> getAll() {
        return null;
    }
}
