package chess.dao;

public class SQLExecuteException extends RuntimeException {
    public SQLExecuteException(String message) {
        super(message);
    }
}
