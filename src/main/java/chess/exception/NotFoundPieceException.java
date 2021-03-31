package chess.exception;

public class NotFoundPieceException extends RuntimeException{
    private static final String MESSAGE = "체스 말을 찾을 수 없습니다.";

    public NotFoundPieceException() {
        super(MESSAGE);
    }

}
