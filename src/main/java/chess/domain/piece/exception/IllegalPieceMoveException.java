package chess.domain.piece.exception;

public class IllegalPieceMoveException extends RuntimeException {

    public IllegalPieceMoveException() {
        super("잘못된 기물 움직임 요청입니다.");
    }
}
