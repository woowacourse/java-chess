package chess.domain;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    private void validateRange(int x, int y) {
        validateColumn(x);
        validateRow(y);
    }

    private void validateColumn(int x) {
        if (x < 1 || x > 8) {
            throw new IllegalArgumentException("올바르지 않은 열입니다.");
        }
    }

    private void validateRow(int y) {
        if (y < 1 || y > 8) {
            throw new IllegalArgumentException("올바르지 않은 행입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
