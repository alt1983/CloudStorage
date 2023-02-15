package ru.netology.cloudstorage.exception;

public class ErrorUnauthorized extends RuntimeException {

    private final int id = 400;

    int getId(){
        return  this.id;
    }

    public ErrorUnauthorized(String msg) {
        super(msg);
    }
}
