package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

//@Entity
//@Table(name = "users")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class User {

    private String userId;
    private Contacts contacts;

    private String login;
    private String password;

    private String name;
    private String surname;
    private UserStatus userStatus;

    private LocalDate birthDate;
    private boolean banned;

/*
    public User(String userId, Contacts contacts, String login, String password, String name, String surname, UserStatus userStatus, Timestamp birthDate, boolean banned) {
        this.userId = userId;
        this.contacts = contacts;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userStatus = userStatus;
        this.birthDate = birthDate;
        this.banned = banned;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userId='" + userId + '\'' +
                ", contacts=" + contacts +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userStatus=" + userStatus +
                ", birthDate=" + birthDate +
                ", banned=" + banned +
                '}';
    }*/

}
