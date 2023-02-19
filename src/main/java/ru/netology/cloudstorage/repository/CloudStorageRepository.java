package ru.netology.cloudstorage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CloudStorageRepository {

    private final FilesRepository filesRepository;
    private final UsersRepository usersRepository;
    private final TokensRepository tokensRepository;
    private Map<String, Boolean> tokens;

    public CloudStorageRepository(FilesRepository filesRepository, UsersRepository usersRepository, TokensRepository tokensRepository) {
        this.filesRepository = filesRepository;
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
    }

    public List<Files> findAllFiles() {
        return filesRepository.findAllFiles();
    }

    public List<Files> findByNameFile(String name) {
        return filesRepository.findByName(name);
    }

    public void deactivateFile(String name) {
        filesRepository.deactivateFile(name);
    }

    public void updateDataFile(String name, byte[] data) {
        filesRepository.updateData(name, data);
    }

    public boolean insertFile(String name, byte[] data) {
        filesRepository.insertFile(name, data);
        return true;
    }

    public List<Tokens> findAllTokens() {
        return tokensRepository.findAllTokens();
    }

    public List<Tokens> findByToken(String token) {
        return tokensRepository.findByToken(token);
    }

    public void deactivateToken(String token) {
        tokensRepository.deactivateToken(token);
    }

    public void insertToken(String token) {
        tokensRepository.insertToken(token);
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAllUsers();
    }

    public List<Users> findByLoginUser(String login) {
        return usersRepository.findByLogin(login);
    }

    public List<Users> findByLoginPasswordUser(String login, String password) {
        return usersRepository.findByLoginPassword(login, password);
    }

    public void deactivateUser(String login) {
        usersRepository.deactivateUser(login);
    }

    public void deleteFile(String login) {
        filesRepository.deleteFile(login);
    }

    public void updateName(String filename, String name) {
        filesRepository.updateName(filename, name);
    }

    public void setTokens(Map<String, Boolean> tokens) {
        this.tokens = tokens;
    }
}
