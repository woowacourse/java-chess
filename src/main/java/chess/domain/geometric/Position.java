package chess.domain.geometric;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 8;
    private static final List<List<Position>> positions;

    static {
        positions = new ArrayList<>();

        for (int i = MIN_POSITION; i < MAX_POSITION; i++) {
            List<Position> row = new ArrayList<>();
            for (int j = MIN_POSITION; j < MAX_POSITION; j++) {
                row.add(new Position(i, j));
            }
            positions.add(row);
        }
    }

    private final int x;
    private final int y;

    private Position(final int x, final int y) {
        validatePosition(x);
        validatePosition(y);
        this.x = x;
        this.y = y;
    }

    private void validatePosition(final int x) {
        if (x < MIN_POSITION || x >= MAX_POSITION) {
            throw new IllegalPositionException("범위를 벗어난 포지션입니다.");
        }
    }

    public static Position create(final int x, final int y) {
        return positions.get(x).get(y);
    }

    public int calculateXDistance(Position position) {
        return this.x - position.x;
    }

    public int calculateYDistance(Position position) {
        return this.y - position.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
