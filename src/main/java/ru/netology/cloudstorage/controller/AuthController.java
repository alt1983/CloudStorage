package ru.netology.cloudstorage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudstorage.domain.FileInfo;
import ru.netology.cloudstorage.domain.FileName;
import ru.netology.cloudstorage.repository.CloudStorageRepository;
import ru.netology.cloudstorage.repository.Files;
import ru.netology.cloudstorage.service.AuthService;
import ru.netology.cloudstorage.service.CloudStorageService;
import ru.netology.cloudstorage.service.JwtRequest;
import ru.netology.cloudstorage.service.JwtResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;
    private CloudStorageRepository repository;
    private CloudStorageService cloudStorageService;

    public AuthController(AuthService authService, CloudStorageRepository repository, CloudStorageService cloudStorageService) {
        this.authService = authService;
        this.repository = repository;
        this.cloudStorageService = cloudStorageService;
    }

    @GetMapping("list")
    public List<FileInfo> getList(@RequestParam("limit") Integer limit) {
        return cloudStorageService.getFilesList();
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        HashMap<String, String> map = new HashMap<>();
        map.put("auth-token", token.getAccessToken());
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok("Success logout");
    }

    @PostMapping("file")
    public ResponseEntity<?> uploadFile(@RequestParam("filename") String filename, @RequestPart("file") MultipartFile f) throws IOException {
        cloudStorageService.uploadFile(filename, f.getBytes());
        return ResponseEntity.ok("Success upload");
    }

    @GetMapping("file")
    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) {
        Files fl = cloudStorageService.downloadFile(filename);
        return ResponseEntity.ok().body(fl.getData());
    }

    @DeleteMapping("file")
    public ResponseEntity<?> deleteFile(@RequestParam("filename") String filename) {
        cloudStorageService.deleteFile(filename);
        return ResponseEntity.ok("Success deleted");
    }

    @PutMapping("file")
    public ResponseEntity<?> putFile(@RequestParam("filename") String filename, @RequestBody FileName name) {
        cloudStorageService.putFile(filename, name.getFilename());
        return ResponseEntity.ok("Success upload");
    }
}
