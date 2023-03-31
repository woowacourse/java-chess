package chess.dao;

public class DBConnectionException extends RuntimeException {
    public DBConnectionException(String message) {
        super(message);
    }
}
