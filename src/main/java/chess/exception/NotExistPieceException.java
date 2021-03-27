package chess.exception;

public class NotExistPieceException extends IllegalStateException {
    public NotExistPieceException() {
        super("존재하지 않는 말입니다.");
    }
}
