package chess;

public class ConnectionFailureException extends RuntimeException{
    public ConnectionFailureException(String message) {
        super(message);
    }
}
