package chess.domain.exceptions;

public class UnableMoveTypeException extends RuntimeException {

    public UnableMoveTypeException() {
        super("해당 말이 이동할 수 있는 위치가 아닙니다.");
    }
}
