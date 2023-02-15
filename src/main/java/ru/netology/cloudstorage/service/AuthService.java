package ru.netology.cloudstorage.service;

import lombok.NonNull;
import ru.netology.cloudstorage.exception.ErrorInputData;
import ru.netology.cloudstorage.repository.CloudStorageRepository;
import ru.netology.cloudstorage.repository.Users;
import ru.netology.cloudstorage.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final JwtFilter jwtFilter;
    private CloudStorageRepository repository;
    private String activeToken;

    public AuthService(JwtProvider jwtProvider, JwtFilter jwtFilter, CloudStorageRepository repository) {
        this.jwtProvider = jwtProvider;
        this.jwtFilter = jwtFilter;
        this.repository = repository;
    }

    public void logout() {
        repository.deactivateToken(this.activeToken);
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        String login = authRequest.getLogin();
        final Users users = (repository.findByLoginUser(login).stream()
                .filter(us -> login.equals(us.getLogin()))
                .findFirst())
                .orElseThrow(() -> new ErrorInputData("Bad credentials"));
        final User user = new User(users);
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            repository.insertToken(accessToken);
            this.activeToken = accessToken;
            return new JwtResponse(accessToken);
        } else {
            throw new ErrorInputData("Bad credentials");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
