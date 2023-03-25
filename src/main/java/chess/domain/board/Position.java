package chess.domain.board;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class Position {

    private static final Map<Integer, Position> CACHE = new ConcurrentHashMap<>(64);
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 7;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        if (validateRange(x) || validateRange(y)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 위치 값 입니다.");
        }
        return CACHE.computeIfAbsent(Objects.hash(x, y), ignore -> new Position(x, y));
    }

    private static boolean validateRange(int range) {
        return range < MIN_RANGE || range > MAX_RANGE;
    }

    public boolean isSameXAs(Position other) {
        return this.x == other.x;
    }

    public boolean isSameYAs(Position other) {
        return this.y == other.y;
    }

    public boolean isSameY(int y) {
        return this.y == y;
    }

    public int getXDistanceTo(Position other) {
        return Math.abs(this.x - other.x);
    }

    public int getYDistanceTo(Position other) {
        return Math.abs(this.y - other.y);
    }

    public boolean isOverThanYTo(Position other) {
        return this.y > other.y;
    }

    public List<Position> getBetweenPositions(Position target) {
        int xDistance = target.x - this.x;
        int yDistance = target.y - this.y;
        int distance = getDistanceTo(target);
        int xFactor = xDistance / distance;
        int yFactor = yDistance / distance;
        return IntStream.range(1, distance)
                .mapToObj(value -> Position.of((this.x + xFactor * value), (this.y + yFactor * value)))
                .collect(toList());
    }

    public int getDistanceTo(Position other) {
        int xDistance = Math.abs(this.x - other.x);
        int yDistance = Math.abs(this.y - other.y);
        return Math.max(xDistance, yDistance);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
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
