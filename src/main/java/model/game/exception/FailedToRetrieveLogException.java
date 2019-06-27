package model.game.exception;

public class FailedToRetrieveLogException extends RuntimeException {
    public FailedToRetrieveLogException(Exception e) {
        super(e);
    }
}