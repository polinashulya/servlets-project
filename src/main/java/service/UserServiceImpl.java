package service;

import entity.User;

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService{



    @Override
    public void add(String login, String password, String confirmedPassword, String name, String surname, Date birthDate, String email, String phoneNumber) {

    }

    @Override
    public User getById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
