package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position implements Comparable<Position> {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 8;
    private static final List<Position> positions = new ArrayList<>();

    private final Coordinate x;
    private final Coordinate y;

    static {
        for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
            for (int j = MIN_BOUND; j <= MAX_BOUND; ++j) {
                positions.add(new Position(Coordinate.getCoordinate(i), Coordinate.getCoordinate(j)));
            }
        }
    }

    private Position(final Coordinate x, final Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public Position move(Direction direction) {
        return new Position(x.move(direction.getDirectionX()),
                y.move(direction.getDirectionY()));
    }

    public boolean canMove(Direction direction) {
        return x.canMove(direction.getDirectionX())
                && y.canMove(direction.getDirectionY());
    }

    public boolean isSameCoordinateX(int x) {
        return this.x.equals(Coordinate.getCoordinate(x));
    }

    public static Position getPosition(final int x, final int y) {
        return positions.stream()
                .filter(position -> position.x.isSame(x) && position.y.isSame(y))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치를 찾을 수 없습니다."))
                ;
    }

    public int getCoordinateX() {
        return x.getValue();
    }

    public int getCoordinateY() {
        return y.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}\n";
    }

    @Override
    public int compareTo(Position o) {
        int result = this.y.compareTo(o.y);
        if (result == 0) {
            return this.x.compareTo(o.x);
        }
        return result;
    }
}
