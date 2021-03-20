package chess.domain.piece;

import java.util.Objects;
import java.util.stream.IntStream;

public final class Position {

    private static final int DEFAULT_MOVE_LENGTH = 1;
    private static final int POSITION_MAX_SIZE = 8;
    private static final Position[] positions = new Position[POSITION_MAX_SIZE * POSITION_MAX_SIZE];

    private final Point x;
    private final Point y;

    static {
        for (int x = 0; x < POSITION_MAX_SIZE; x++) {
            for (int y = 0; y < POSITION_MAX_SIZE; y++) {
                Point pointX = Point.from(x);
                Point pointY = Point.from(y);
                positions[asIndex(pointX, pointY)] = new Position(x, y);
            }
        }
    }

    private Position(int x, int y) {
        this(Point.from(x), Point.from(y));
    }

    private Position(Point x, Point y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(String value) {
        int x = value.charAt(1) - '1';
        int y = value.charAt(0) - 'a';
        return of(x, y);
    }

    public static Position of(Point x, Point y) {
        return of(x.point(), y.point());
    }

    public static Position of(int x, int y) {
        return positions[asIndex(Point.from(x), Point.from(y))];
    }

    private static int asIndex(Point x, Point y) {
        return x.point() * POSITION_MAX_SIZE + y.point();
    }

    public boolean canMove(Position position, Direction direction, int ableLength) {
        return IntStream.rangeClosed(DEFAULT_MOVE_LENGTH, ableLength)
            .filter(distance -> isAdd(direction.getXDegree() * distance, direction.getYDegree() * distance))
            .mapToObj(distance -> {
                    Point dx = x.add(direction.getXDegree() * distance);
                    Point dy = y.add(direction.getYDegree() * distance);
                    return new Position(dx, dy);
            })
            .anyMatch(position::equals);
    }

    public boolean isAdd(int x, int y) {
        return this.x.isAdd(x) && this.y.isAdd(y);
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

    public Point getX() {
        return x;
    }

    public Position add(int x, int y) {
        return Position.of(this.x.add(x), this.y.add(y));
    }

    public boolean isSameColumn(Point point) {
        return y.equals(point);
    }
}
