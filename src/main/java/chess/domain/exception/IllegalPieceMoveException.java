package chess.domain.exception;

public class IllegalPieceMoveException extends RuntimeException {

    public IllegalPieceMoveException() {
        super("잘못 된 기물 움직임 요청입니다.");
    }
}
