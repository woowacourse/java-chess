package chess.domains.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
    private static final Map<String, Position> cachedPositions;

    static {
        Map<String, Position> positions = new HashMap<>();
        for (char x = 'a'; x <= 'h'; x++) {
            for (int y = 1; y <= 8; y++) {
                Position position = new Position(x, y);
                positions.put(x + String.valueOf(y), position);
            }
        }
        cachedPositions = positions;
    }

    private char x;
    private int y;

    public Position(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<String> fromRow(String row) {
        return cachedPositions.keySet().stream()
                .filter(key -> key.endsWith(row)).sorted().collect(Collectors.toList());
    }

    public static Position ofPositionName(String positionName) {
        return cachedPositions.get(positionName);
    }

    public int xGapBetween(Position target) {
        return target.x - this.x;
    }

    public int yGapBetween(Position target) {
        return target.y - this.y;
    }

    public boolean isSameX(Position target) {
        return this.x == target.x;
    }

    public boolean isSameY(Position target) {
        return this.y == target.y;
    }

    public boolean isRow(int row) {
        return this.y == row;
    }

    @Override
    public int compareTo(Position o) {
        if (y > o.y) {
            return -1;
        }
        if (y == o.y && x < o.x) {
            return -1;
        }
        return 1;
    }

    public List<Position> findRoute(Position target) {
        ArrayList<Position> route = new ArrayList<>();
        Direction direction = findDirection(target);

        char x = (char) (this.x + direction.xGap);
        int y = this.y + direction.yGap;

        while (x != target.x || y != target.y) {
            route.add(new Position(x, y));
            x += direction.xGap;
            y += direction.yGap;
        }

        return route;
    }

    public Direction findDirection(Position target) {
        int yGap = this.yGapBetween(target);
        int xGap = this.xGapBetween(target);

        return Direction.findDirection(xGap, yGap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isColumn(char column) {
        return this.x == column;
    }
}
