package model.game.exception;

public class FailedToCreateLogTableException extends RuntimeException {
    public FailedToCreateLogTableException(Exception e) {
        super(e);
    }
}