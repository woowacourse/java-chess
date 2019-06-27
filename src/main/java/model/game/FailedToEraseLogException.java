package model.game;

public class FailedToEraseLogException extends RuntimeException {
    public FailedToEraseLogException(Exception e) {
        super(e);
    }
}