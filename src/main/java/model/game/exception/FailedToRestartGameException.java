package model.game.exception;

public class FailedToRestartGameException extends RuntimeException {
    public FailedToRestartGameException(Exception e) {
        super(e);
    }
}