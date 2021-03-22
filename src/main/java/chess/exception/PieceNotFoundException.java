package chess.exception;

public class PieceNotFoundException extends ChessException {

    private static final String MESSAGE = "해당하는 말을 찾을 수 없습니다.";

    public PieceNotFoundException() {
        super(MESSAGE);
    }
}
