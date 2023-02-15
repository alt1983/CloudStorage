package ru.netology.cloudstorage.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File {

    public String hash;
    public String file;

    public File() {
    }

    @Override
    public String toString() {
        return "File{" +
                "hash='" + hash + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}



