package ru.netology.cloudstorage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.netology.cloudstorage.repository.Users;

import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

    public User(Users users) {
        this.login = users.getLogin();
        this.password = users.getPassword();
        this.firstName = users.getFirstname();
        this.lastName = users.getLastname();
        if (users.getRole().equals("USER")) this.roles = Collections.singleton(Role.USER);
        if (users.getRole().equals("ADMIN")) this.roles = Collections.singleton(Role.ADMIN);
    }

}
