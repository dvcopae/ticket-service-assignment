package me.dvcopae.tickets.exceptions;

public class DatabaseSetupException extends RuntimeException {
  public DatabaseSetupException(String message) {
    super(message);
  }

  public DatabaseSetupException(Throwable cause) {
    super(cause);
  }
}
