package chess.domain.piece;

import java.util.Objects;
import java.util.stream.IntStream;

public final class Position {

    private static final int DEFAULT_MOVE_LENGTH = 1;
    private static final int POSITION_MAX_SIZE = 8;
    private static final Position[] positions = new Position[POSITION_MAX_SIZE * POSITION_MAX_SIZE];
    private static final int INDEX_X = 1;
    private static final int INDEX_Y = 0;
    private static final char CORRECTION_X = '1';
    private static final char CORRECTION_Y = 'a';

    static {
        IntStream.range(0, POSITION_MAX_SIZE)
            .forEach(x -> IntStream.range(0, POSITION_MAX_SIZE)
                .forEach(y -> positions[asIndex(x, y)] = new Position(x, y)));
    }

    private final Point x;
    private final Point y;

    private Position(final int x, final int y) {
        this(Point.from(x), Point.from(y));
    }

    private Position(final Point x, final Point y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final Point x, final Point y) {
        return of(x.point(), y.point());
    }

    public static Position of(final String value) {
        int x = value.charAt(INDEX_X) - CORRECTION_X;
        int y = value.charAt(INDEX_Y) - CORRECTION_Y;
        return of(x, y);
    }

    public static Position of(final int x, final int y) {
        return positions[asIndex(x, y)];
    }

    private static int asIndex(final int x, final int y) {
        return x * POSITION_MAX_SIZE + y;
    }

    public boolean canMove(final Position position, final Direction direction, final int ableLength) {
        return IntStream.rangeClosed(DEFAULT_MOVE_LENGTH, ableLength)
            .filter(distance -> isAdd(direction.getXDegree() * distance,
                direction.getYDegree() * distance))
            .mapToObj(distance -> {
                Point dx = x.add(direction.getXDegree() * distance);
                Point dy = y.add(direction.getYDegree() * distance);
                return new Position(dx, dy);
            })
            .anyMatch(position::equals);
    }

    public boolean isAdd(final int x, final int y) {
        return this.x.isAdd(x) && this.y.isAdd(y);
    }

    public Position add(final int x, int y) {
        return Position.of(this.x.add(x), this.y.add(y));
    }

    public boolean isSameColumn(final Point point) {
        return y.equals(point);
    }

    public boolean isSameRow(final Point point) {
        return x.equals(point);
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
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
