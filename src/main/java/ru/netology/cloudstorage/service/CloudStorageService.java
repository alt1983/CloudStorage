package ru.netology.cloudstorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.domain.FileInfo;
import ru.netology.cloudstorage.exception.ErrorInputData;
import ru.netology.cloudstorage.exception.ErrorInternal;
import ru.netology.cloudstorage.repository.CloudStorageRepository;
import ru.netology.cloudstorage.repository.Files;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloudStorageService {
    private AuthService authService;
    private CloudStorageRepository repository;

    public CloudStorageService(AuthService authService, CloudStorageRepository repository) {
        this.authService = authService;
        this.repository = repository;
    }

    public List<FileInfo> getFilesList(Integer limit) {

        if(limit <= 0) throw new ErrorInputData("Error amount of files");
        List<FileInfo> filesInfo = new ArrayList<>();
        List<Files> files = repository.findAllFiles();
        int lim = limit;
        for (Files file : files) {
            if(lim > 0) {
                filesInfo.add(new FileInfo(file.getName(), file.getData().length, String.valueOf(file.hashCode())));
                lim--;
            }
        }
        if (filesInfo.isEmpty()) throw new ErrorInternal("Error getting file list");
        return filesInfo;

    }

    public boolean uploadFile(String filename, byte[] data) {
        if (filename == null) throw new ErrorInputData("Error input data");
        return repository.insertFile(filename, data);
    }

    public void putFile(String filename, String name) {
        if (filename == null || name == null) throw new ErrorInputData("Error input data");
        repository.updateName(filename, name);
    }

    public void deleteFile(String filename) {
        if (filename == null) throw new ErrorInputData("Error input data");
        repository.deleteFile(filename);
    }

    public Files downloadFile(String filename) {
        if (filename == null) throw new ErrorInputData("Error input data");
        List<Files> files = repository.findByNameFile(filename);
        Files fl = null;
        for (Files file : files) {
            fl = file;
        }
        if (fl == null) {
            throw new ErrorInternal("Error upload file");
        }
        return fl;
    }


}
