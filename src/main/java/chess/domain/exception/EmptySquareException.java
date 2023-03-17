package chess.domain.exception;

public class EmptySquareException extends IllegalArgumentException {

    public EmptySquareException() {
        super("해당 칸에 말이 존재하지 않습니다.");
    }
}
