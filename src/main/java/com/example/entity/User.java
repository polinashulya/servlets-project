package com.example.entity;

import lombok.*;

import java.time.LocalDate;

//@Entity
//@Table(name = "users")
@Data
@Builder
public class User {

    private Long id;

    private String login;
    private String password;

    private String firstName;
    private String secondName;
    private UserStatus userStatus;

    private LocalDate birthDate;
    private boolean banned;

    public User(Long id, String login, String password, String firstName, String secondName, UserStatus userStatus, LocalDate birthDate, boolean banned) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userStatus = userStatus;
        this.birthDate = birthDate;
        this.banned = banned;
    }
}
