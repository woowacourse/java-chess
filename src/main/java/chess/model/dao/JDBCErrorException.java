package chess.model.dao;

public class JDBCErrorException extends RuntimeException {
    public JDBCErrorException(String message) {
        super(message);
    }
}