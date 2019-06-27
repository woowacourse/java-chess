package model.game;

public class FailedToWriteLogException extends RuntimeException {
    public FailedToWriteLogException(Exception e) {
        super(e);
    }
}