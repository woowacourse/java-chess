package model.game;

public class FailedToCreateLogTableException extends RuntimeException {
    public FailedToCreateLogTableException(Exception e) {
        super(e);
    }
}