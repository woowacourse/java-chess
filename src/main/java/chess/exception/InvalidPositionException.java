package chess.exception;

public class InvalidPositionException extends ChessException {

    private static final String MESSAGE = "체스판의 범위를 넘어서는 숫자입니다.";

    public InvalidPositionException() {
        super(MESSAGE);
    }
}
