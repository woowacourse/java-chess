package model.game;

public class FailedToRetrieveLogException extends RuntimeException {
    public FailedToRetrieveLogException(Exception e) {
        super(e);
    }
}