package chess.repository;

public class DatabaseConnectionFailException extends RuntimeException {

    public DatabaseConnectionFailException() {
        super("데이터베이스 연결에 실패했습니다.");
    }
}
