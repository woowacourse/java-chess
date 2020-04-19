package chess.db;

public class SQLExecuteException extends RuntimeException {
    public SQLExecuteException(String message) {
        super(message);
    }
}
