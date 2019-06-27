package model.game.exception;

public class FailedToWriteLogException extends RuntimeException {
    public FailedToWriteLogException(Exception e) {
        super(e);
    }
}