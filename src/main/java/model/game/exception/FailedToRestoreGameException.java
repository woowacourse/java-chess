package model.game.exception;

public class FailedToRestoreGameException extends RuntimeException {
    public FailedToRestoreGameException(Exception e) {
        super(e);
    }

    public FailedToRestoreGameException() {}
}