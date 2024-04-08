package co.istad.testmobilebankingapi.features.card_types.exception;

public class CardTypeException extends RuntimeException {
    public CardTypeException(String message) {
        super(message);
    }

    public CardTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
