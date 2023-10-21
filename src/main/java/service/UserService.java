package service;

import entity.User;

import java.sql.Date;
import java.util.List;

public interface UserService {

    void add(String login, String password, String confirmedPassword,
             String name, String surname, Date birthDate,
             String email, String phoneNumber);

    User getById(String userId);

    List<User> getAll() ;

}
