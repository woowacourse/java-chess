package chess.dao.exception;

public class SelectQueryException extends RuntimeException{
    private static final String MESSAGE = "Select Query가 실행될 수 없습니다.";

    public SelectQueryException() {
        super(MESSAGE);
    }
}
