package chess.domain.pieces;

public class Position {
    public static final int RANGE_MIN_PIVOT = 0;
    public static final int RANGE_MAX_PIVOT = 7;
    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    private void validateRange(final int x, final int y) {
        if (x < RANGE_MIN_PIVOT || x > RANGE_MAX_PIVOT || y < RANGE_MIN_PIVOT || y > RANGE_MAX_PIVOT) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
    }
}
