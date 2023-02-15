package ru.netology.cloudstorage.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TokensRepository extends JpaRepository<Tokens, Integer> {

    @Query("select p from Tokens p")
    List<Tokens> findAllTokens();

    @Query("select p from Tokens p where p.token = :token and p.active = true")
    List<Tokens> findByToken(@Param("token") String token);

    @Modifying
    @Query(value = "insert into netology.TOKENS (token, active) VALUES (:token,true)", nativeQuery = true)
    @Transactional
    void insertToken(@Param("token") String token);

    @Modifying
    @Transactional
    @Query("update Tokens p set p.active = false where p.token=:token")
    void deactivateToken(@Param("token") String token);

}
