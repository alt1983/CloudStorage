package ru.netology.cloudstorage.exception;

public class ErrorInternal extends RuntimeException {

    private final int id = 500;

    int getId(){
        return  this.id;
    }

    public ErrorInternal(String msg) {
        super(msg);
    }
}
