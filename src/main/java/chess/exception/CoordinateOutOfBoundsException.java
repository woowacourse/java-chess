package chess.exception;

public class CoordinateOutOfBoundsException extends IllegalArgumentException {
    public CoordinateOutOfBoundsException() {
        super("범위를 벗어난 좌표값입니다.");
    }

    public CoordinateOutOfBoundsException(String s) {
        super(s);
    }
}
