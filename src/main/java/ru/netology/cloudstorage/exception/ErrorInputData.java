package ru.netology.cloudstorage.exception;

public class ErrorInputData extends RuntimeException {

    private final int id = 400;

    int getId() {
        return this.id;
    }

    public ErrorInputData(String msg) {
        super(msg);
    }
}
