package chess.exception;

public class PawnMoveDiagonalException extends RuntimeException {
    private static final String ERROR_MESSAGE = "폰은 상대 팀이 있을 때만 대각선으로 움직일 수 있습니다.";

    public PawnMoveDiagonalException() {
        super(ERROR_MESSAGE);
    }
}
