package com.cenihub.exception;

public class FailedToDeleteException extends RuntimeException {
    public FailedToDeleteException(Throwable cause) {
        super("Failed to delete record!: " + cause);
    }
}
