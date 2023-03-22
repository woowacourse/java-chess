package chess.domain.exception;

public class SquareOutOfBoundException extends IllegalArgumentException {

    public SquareOutOfBoundException() {
        super("체스판의 범위를 벗어났습니다.");
    }
}
