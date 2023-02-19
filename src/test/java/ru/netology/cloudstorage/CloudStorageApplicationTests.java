package ru.netology.cloudstorage;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudstorage.controller.AuthController;
import ru.netology.cloudstorage.domain.FileInfo;
import ru.netology.cloudstorage.domain.Role;
import ru.netology.cloudstorage.exception.ErrorInputData;
import ru.netology.cloudstorage.repository.CloudStorageRepository;
import ru.netology.cloudstorage.repository.Users;
import ru.netology.cloudstorage.service.*;
import org.springframework.http.ResponseEntity;
import ru.netology.cloudstorage.domain.User;
import ru.netology.cloudstorage.repository.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SpringBootTest
class CloudStorageApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getLoginDataTest() {
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        User us = new User("user1", "user1", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        Mockito.when(authRequest.getLogin()).thenReturn(us.getLogin());
        Mockito.when(authRequest.getPassword()).thenReturn(us.getPassword());
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.findByLoginUser(user.getLogin())).thenReturn(Arrays.asList(user));
        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);
        Assert.assertNotNull(authController.login(authRequest));
        Assert.assertEquals(authController.login(authRequest).getClass(), ResponseEntity.class);

    }

    @Test
    void getLoginErrorInputData() {
        boolean errorInputData = false;
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        User us = new User("user1", "test", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        Mockito.when(authRequest.getLogin()).thenReturn(us.getLogin());
        Mockito.when(authRequest.getPassword()).thenReturn(us.getPassword());
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.findByLoginUser(user.getLogin())).thenReturn(Arrays.asList(user));
        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);

        try {
            ResponseEntity<?> res = authController.login(authRequest);
        } catch (ErrorInputData e) {
            errorInputData = true;
            System.out.println("true");
        }
        Assert.assertTrue(errorInputData);
    }

    @Test
    void getListDataTest() {
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        Integer limit = 2;
        User us = new User("user1", "user1", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        List<Files> files = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            files.add(new Files(i, String.valueOf(i), new byte[i], true));
        }
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.findAllFiles()).thenReturn(files);

        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);
        Assert.assertNotNull(authController.getList(limit));
        Assert.assertEquals(authController.getList(limit).getClass(), ArrayList.class);
    }

    @Test
    void getListErrorInputData() {
        boolean errorInputData = false;
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        Integer limit = 0;
        User us = new User("user1", "user1", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        List<Files> files = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            files.add(new Files(i, String.valueOf(i), new byte[i], true));
        }
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.findAllFiles()).thenReturn(files);

        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);

        try {
            List<FileInfo> fileInfo = authController.getList(limit);
        } catch (ErrorInputData e) {
            errorInputData = true;
        }
        Assert.assertTrue(errorInputData);
    }

    @Test
    void getUploadFileDataTest() throws IOException {
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        String name = "test";
        byte[] data = new byte[0];
        User us = new User("user1", "user1", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.insertFile(name, data)).thenReturn(true);
        MultipartFile f = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);
        Assert.assertNotNull(authController.uploadFile(name, f));
        Assert.assertEquals(authController.uploadFile(name, f).getClass(), ResponseEntity.class);
    }

    @Test
    void getUploadFileErrorInputData() {
        boolean errorInputData = false;
        Users user = new Users();
        user.setLogin("user1");
        user.setPassword("user1");
        user.setRole("USER");
        String name = null;
        byte[] data = new byte[0];
        User us = new User("user1", "user1", "user1", "user1", Collections.singleton(Role.USER));
        JwtRequest authRequest = Mockito.mock(JwtRequest.class);
        CloudStorageRepository repository = Mockito.mock(CloudStorageRepository.class);
        Mockito.when(repository.insertFile(name, data)).thenReturn(true);
        MultipartFile f = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        JwtProvider jwtProvider = Mockito.mock(JwtProvider.class);
        Mockito.when(jwtProvider.generateAccessToken(us)).thenReturn("test");
        AuthService authService = new AuthService(jwtProvider, new JwtFilter(jwtProvider), repository);
        CloudStorageService cloudStorageService = new CloudStorageService(authService, repository);
        AuthController authController = new AuthController(authService, repository, cloudStorageService);
        try {
            ResponseEntity<?> res = authController.uploadFile(name, f);
        } catch (ErrorInputData e) {
            errorInputData = true;
            System.out.println(true);
        } catch (IOException e) {
        }
        Assert.assertTrue(errorInputData);
    }


}
