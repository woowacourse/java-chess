package chess.model;

public class Position {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 8;
    private final int x;
    private final int y;

    public Position(int x, int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    private void validateRange(int x, int y) {
        if (x < MIN_VALUE || x > MAX_VALUE || y < MIN_VALUE || y > MAX_VALUE) {
            throw new IllegalArgumentException("좌표 값은 1이상 8이하의 값만 가능합니다.");
        }
    }
}
