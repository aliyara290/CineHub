package com.cenihub.exception;

public class RecordNotFound extends RuntimeException {
  public RecordNotFound() {
    super("Record not found!");
  }
}
