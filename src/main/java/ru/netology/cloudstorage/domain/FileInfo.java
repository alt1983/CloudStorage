package ru.netology.cloudstorage.domain;

public class FileInfo {
    private String filename;
    private String hash;
    private Integer size;

    public String getFilename() {
        return filename;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public FileInfo(String filename, Integer size, String hash) {
        this.filename = filename;
        this.size = size;
        this.hash = hash;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }


}
