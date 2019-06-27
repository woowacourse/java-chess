package model.game.exception;

public class FailedToEraseLogException extends RuntimeException {
    public FailedToEraseLogException(Exception e) {
        super(e);
    }
}