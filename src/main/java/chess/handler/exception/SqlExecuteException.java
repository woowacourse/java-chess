package chess.handler.exception;

public class SqlExecuteException extends RuntimeException {

    public SqlExecuteException(String message) {
        super(message);
    }
}
