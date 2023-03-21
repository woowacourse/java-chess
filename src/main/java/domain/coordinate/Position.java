package domain.coordinate;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final List<List<Position>> CACHE;
    public static final int ROW_SIZE = 8;
    public static final int COLUMN_SIZE = 8;

    private final int x;
    private final int y;

    static {
        CACHE = new ArrayList<>();
        createPositions();
    }

    private Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final int x, final int y) {
        return CACHE.get(y)
                .get(x);
    }

    private static void createPositions() {
        for (int y = 0; y < ROW_SIZE; y++) {
            CACHE.add(new ArrayList<>());
            addRow(y);
        }
    }

    private static void addRow(final int y) {
        for (int x = 0; x < COLUMN_SIZE; x++) {
            CACHE.get(y).add(new Position(x, y));
        }
    }

    public int diffY(Position otherPosition) {
        return this.y - otherPosition.y;
    }

    public int diffX(Position otherPosition) {
        return this.x - otherPosition.x;
    }

    public Position move(int moveX, int moveY) {
        return Position.of(x + moveX, y + moveY);
    }

    public boolean isDiagonally(Position otherPosition) {
        final int diffX = Math.abs(this.diffX(otherPosition));
        final int diffY = Math.abs(this.diffY(otherPosition));

        return (diffX != 0 || diffY != 0) && (diffX == diffY);
    }

    public boolean isStraight(Position otherPosition) {
        final int diffX = Math.abs(this.diffX(otherPosition));
        final int diffY = Math.abs(this.diffY(otherPosition));

        return (diffX != 0 || diffY != 0) && (diffX == 0 || diffY == 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
