package chess.domain;

import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        validate(x, y);
        this.x = x;
        this.y = y;
    }

    private void validate(final int x, final int y) {
        if (!isRangeValid(x) || !isRangeValid(y)) {
            throw new IllegalArgumentException("좌표의 값은 1 ~ 8 사이여야 합니다.");
        }
    }

    private boolean isRangeValid(final int value) {
        return value >= 1 && value <= 8;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
