package com.arun.semperf.analysis.exceptions;

// A base class for custom unchecked exceptions the analytics
// tool use.
public class KnownException extends RuntimeException {

  private final String message;

  private final int errorCode;

  public KnownException(final String message, final int errorCode) {
    super(message);
    this.message = message;
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }
}
