package chess.exception;

public class PieceDoesNotExistException extends ChessException {

    private static final String DOES_NOT_EXIST_MESSAGE = "해당 칸에는 기물이 존재하지 않습니다.";

    public PieceDoesNotExistException() {
        super(DOES_NOT_EXIST_MESSAGE);
    }
}
