package chess.exception;

public class NotFoundPiecesException extends IllegalArgumentException {
    public NotFoundPiecesException() {
        super("존재하지 않는 체스 기물입니다.");
    }

    public NotFoundPiecesException(String s) {
        super(s);
    }
}
