package com.cenihub.exception;

public class FailedToInsertToDb extends RuntimeException {
    public FailedToInsertToDb(String message) {
        super(message);
    }

    public FailedToInsertToDb(Throwable cause) {
        super("Failed to insert data", cause);
    }
}
