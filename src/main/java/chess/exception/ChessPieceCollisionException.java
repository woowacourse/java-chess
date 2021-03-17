package chess.exception;

public class ChessPieceCollisionException extends RuntimeException {
    private static final String MESSAGE = "이미 다른말이 존재합니다.";

    public ChessPieceCollisionException() {
        super(MESSAGE);
    }
}
