package chess.exception;

public class ChessDBException extends RuntimeException {

    public ChessDBException(final String message) {
        super("데이터를 불러오는 데 실패했습니다.");
    }
}
