package chess.domain.piece.exception;

public class WrongDirectionException extends IllegalArgumentException {

    public WrongDirectionException() {
        super("이동할 수 없는 위치입니다.");
    }
}
