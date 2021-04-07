package exception;

public class PieceMoveException extends IllegalArgumentException {

    public PieceMoveException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
