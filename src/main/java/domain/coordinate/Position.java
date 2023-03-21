package domain.coordinate;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final Map<Integer, Map<Integer, Position>> CACHE = new HashMap<>();

    private final int x;
    private final int y;

    private Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final int x, final int y) {
        CACHE.computeIfAbsent(y, k -> new HashMap<>())
                .putIfAbsent(x, new Position(x, y));

        return CACHE.get(y)
                .get(x);
    }

    public int diffY(final Position otherPosition) {
        return this.y - otherPosition.y;
    }

    public int diffX(final Position otherPosition) {
        return this.x - otherPosition.x;
    }

    public Position move(final Position direction) {
        return Position.of(this.x + direction.x, this.y + direction.y);
    }

    public boolean isDiagonally(final Position otherPosition) {
        final int diffX = getDiffX(otherPosition);
        final int diffY = getDiffY(otherPosition);

        return (diffX != 0 || diffY != 0) && (diffX == diffY);
    }

    public boolean isStraight(final Position otherPosition) {
        final int diffX = getDiffX(otherPosition);
        final int diffY = getDiffY(otherPosition);

        return (diffX != 0 || diffY != 0) && (diffX == 0 || diffY == 0);
    }

    private int getDiffY(final Position otherPosition) {
        return Math.abs(this.diffY(otherPosition));
    }

    private int getDiffX(final Position otherPosition) {
        return Math.abs(this.diffX(otherPosition));
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
