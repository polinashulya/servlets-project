package entity;

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
    private String surname;
    private UserStatus userStatus;

    private LocalDate birthDate;
    private boolean banned;

}
