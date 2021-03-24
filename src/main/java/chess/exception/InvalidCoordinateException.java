package chess.exception;

public class InvalidCoordinateException extends IllegalArgumentException {
    public InvalidCoordinateException() {
        super("존재하지 않는 좌표입니다.");
    }
}
