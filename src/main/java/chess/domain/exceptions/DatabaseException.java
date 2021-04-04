package chess.domain.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException() {
        super("데이터베이스에 문제가 발생했습니다.");
    }
}
