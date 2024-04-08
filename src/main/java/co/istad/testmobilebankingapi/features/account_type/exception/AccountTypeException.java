package co.istad.testmobilebankingapi.infrastructure.exception;

public class AccountTypeException extends RuntimeException {
    public AccountTypeException(String message) {
        super(message);
    }

    public AccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
