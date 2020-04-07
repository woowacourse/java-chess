package chess.location;

public class NoExistChessLocationException extends IllegalStateException {
    private static final String MESSAGE = "존재하지 않는 Location에 접근하였습니다.";

    NoExistChessLocationException() {
        super(MESSAGE);
    }
}
