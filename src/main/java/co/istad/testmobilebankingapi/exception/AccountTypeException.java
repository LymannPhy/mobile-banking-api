package co.istad.testmobilebankingapi.exception;

public class AccountTypeException extends RuntimeException {
    public AccountTypeException(String message) {
        super(message);
    }

    public AccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
