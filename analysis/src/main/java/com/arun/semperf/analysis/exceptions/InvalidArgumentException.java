package com.arun.semperf.analysis.exceptions;

public class InvalidArgumentException extends KnownException {

  private static final int ERROR_CODE = 101;

  public InvalidArgumentException(final String message) {
    super(message, ERROR_CODE);
  }
}
