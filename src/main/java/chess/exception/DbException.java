package chess.exception;

public class DbException extends RuntimeException {
    public DbException() {
        this("SQL 구문 오류");
    }

    public DbException(String message) {
        super(message);
    }
}
