package chess.util;

public class SqlConnectionException extends RuntimeException{

    public SqlConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
