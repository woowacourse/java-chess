package chess.exception;

public class PawnMoveForwardException extends RuntimeException {
    private static final String ERROR_MESSAGE = "폰은 대각선으로만 상대 진영말을 잡을 수 있습니다.";

    public PawnMoveForwardException() {
        super(ERROR_MESSAGE);
    }
}
