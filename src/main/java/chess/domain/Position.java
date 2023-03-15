package chess.domain;

import java.util.Objects;

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
        if (isNotInRange(x, y)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_OUT_OF_BOUNDS);
        }
    }

    private boolean isNotInRange(final int rank, final int file) {
        return isValueNotInRange(rank) || isValueNotInRange(file);
    }

    private boolean isValueNotInRange(final int positionValue) {
        return positionValue < MINIMUM || positionValue > MAXIMUM;
    }

    public Position findNextPosition(final Direction direction) {
        int nextRank = x + direction.getRankChange();
        int nextFile = y + direction.getFileChange();

        if (isNotInRange(nextRank, nextFile)) {
            return null;
        }

        return new Position(nextRank, nextFile);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
