package com.example.entity;

import lombok.*;

import java.time.LocalDate;

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
    private boolean deleted;



}
