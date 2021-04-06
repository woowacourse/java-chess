package chess.exception;

public class NotPermittedChessPosition extends RuntimeException {
    private static final String MESSAGE = "허용되지 않는 체스 위치입니다.";

    public NotPermittedChessPosition() {
        super(MESSAGE);
    }

}
