package chess.domain.exception;

public class InvalidSquareIndexException extends RuntimeException {

    public InvalidSquareIndexException() {
        super("유효하지 않은 체스판 범위입니다.");
    }
}
