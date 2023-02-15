package ru.netology.cloudstorage.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Integer> {

    @Query("select p from Files p where p.active = true")
    List<Files> findAllFiles();

    @Query("select p from Files p where p.name = :name and p.active = true")
    List<Files> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("update Files p set p.active = false where p.name=:name and p.active = true")
    void deactivateFile(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("delete from Files p where p.name=:name")
    void deleteFile(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("update Files p set p.data = :data where p.name=:name and p.active = true")
    void updateData(@Param("name") String name, @Param("data") byte[] data);

    @Modifying
    @Transactional
    @Query("update Files p set p.name = :name where p.name=:filename and p.active = true")
    void updateName(@Param("filename") String filename, @Param("name") String name);

    @Modifying
    @Query(value = "insert into netology.FILES (name, data, active) VALUES (:name,:data,true)", nativeQuery = true)
    @Transactional
    void insertFile(@Param("name") String name, @Param("data") byte[] data);

}
