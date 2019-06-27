package model.game;

public class FailedToRestoreGameException extends RuntimeException {
    public FailedToRestoreGameException(Exception e) {
        super(e);
    }
}