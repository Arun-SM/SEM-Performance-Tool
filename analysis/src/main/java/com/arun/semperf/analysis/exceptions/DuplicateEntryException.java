package com.arun.semperf.analysis.exceptions;

public class DuplicateEntryException extends KnownException {

  private static final int ERROR_CODE = 103;

  public DuplicateEntryException(final String message) {
    super(message, ERROR_CODE);
  }
}
