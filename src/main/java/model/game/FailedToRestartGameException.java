package model.game;

public class FailedToRestartGameException extends RuntimeException {
    public FailedToRestartGameException(Exception e) {
        super(e);
    }
}