package com.arun.semperf.analysis.exceptions;

public class FileReadException extends KnownException {

  private static final int ERROR_CODE = 102;

  public FileReadException(final String message) {
    super(message, ERROR_CODE);
  }
}
