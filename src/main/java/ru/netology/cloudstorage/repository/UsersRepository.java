package ru.netology.cloudstorage.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select p from Users p")
    List<Users> findAllUsers();

    @Query("select p from Users p where p.login = :login")
    List<Users> findByLogin(@Param("login") String login);

    @Query("select p from Users p where p.login = :login and p.password = :password and p.active = true")
    List<Users> findByLoginPassword(@Param("login") String login, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("update Users p set p.active = false where p.login=:login")
    void deactivateUser(@Param("login") String login);

}
