package chess.domain;

public class Position {

    public static final String EXCEPTION_MESSAGE_OUT_OF_BOUNDS = "말의 위치 범위를 벗어났습니다.";
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;
    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        validate(x, y);
        this.x = x;
        this.y = y;
    }

    private void validate(int x, int y) {
        validatePositionValue(x);
        validatePositionValue(y);
    }

    private void validatePositionValue(int positionValue) {
        if (positionValue < MINIMUM || positionValue > MAXIMUM) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_OUT_OF_BOUNDS);
        }
    }
}
