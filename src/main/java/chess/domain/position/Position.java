package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Position {
    private static final int MINIMUM_POSITION = 1;
    private static final int MAXIMUM_POSITION = 8;

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
        if (x < MINIMUM_POSITION || x > MAXIMUM_POSITION) {
            throw new IllegalArgumentException("올바르지 않은 열입니다.");
        }
    }

    private void validateRow(int y) {
        if (y < MINIMUM_POSITION || y > MAXIMUM_POSITION) {
            throw new IllegalArgumentException("올바르지 않은 행입니다.");
        }
    }

    public Set<Position> findMovablePositions(Set<Direction> directions) {
        return directions.stream()
                .filter(this::isInRange)
                .map(direction -> new Position(direction.getDx() + x, direction.getDy() + y))
                .collect(Collectors.toSet());
    }

    private boolean isInRange(Direction direction) {
        int newX = direction.getDx() + x;
        int newY = direction.getDy() + y;
        return isLocationInRange(newX) && isLocationInRange(newY);
    }

    private boolean isLocationInRange(int location) {
        return location >= MINIMUM_POSITION && location <= MAXIMUM_POSITION;
    }

    public Direction findDirectionTo(Position destination) {
        int dx = destination.x - this.x;
        int dy = destination.y - this.y;

        if (isInEightDirection(dx, dy)) {
            dx = calculateMoved(dx);
            dy = calculateMoved(dy);
            return Direction.findDirection(dx, dy);
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    private boolean isInEightDirection(int dx, int dy) {
        return Math.abs(dx) == Math.abs(dy) || (dx + dy != 0 && dx * dy == 0);
    }

    private int calculateMoved(int dx) {
        if (dx != 0) {
            dx = dx / Math.abs(dx);
        }
        return dx;
    }

    public Set<Position> findCourses(Direction direction, Position other) {
        Set<Position> positions = new HashSet<>();
        int newX = x + direction.getDx();
        int newY = y + direction.getDy();

        while (newX != other.x || newY != other.y) {
            positions.add(new Position(newX, newY));
            newX += direction.getDx();
            newY += direction.getDy();
        }
        return positions;
    }

    public int file() {
        return x;
    }

    public int rank() {
        return y;
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
