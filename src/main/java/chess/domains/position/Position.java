package chess.domains.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
    private static final Map<String, Position> cachedPositions;

    static {
        Map<String, Position> positions = new HashMap<>();
        for (Column x : Column.values()) {
            for (Row y : Row.values()) {
                Position position = new Position(x, y);
                positions.put(x.name() + y.toString(), position);
            }
        }
        cachedPositions = positions;
    }

    private Column x;
    private Row y;

    public Position(Column x, Row y) {
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
        return target.x.columnGap(this.x);
    }

    public int yGapBetween(Position target) {
        return this.y.rowGap(target.y);
    }

    public boolean isSameX(Position target) {
        return this.x == target.x;
    }

    public boolean isSameY(Position target) {
        return this.y == target.y;
    }

    public boolean isRow(int row) {
        return this.y.getRow() == row;
    }

    public List<Position> findRoute(Position target) {
        ArrayList<Position> route = new ArrayList<>();
        Direction direction = findDirection(target);

        Column x = this.x.moveBy(direction.xGap);
        Row y = this.y.moveBy(direction.yGap);

        while (x != target.x || y != target.y) {
            route.add(new Position(x, y));
            x = x.moveBy(direction.xGap);
            y = y.moveBy(direction.yGap);
        }

        return route;
    }

    public Direction findDirection(Position target) {
        int yGap = this.yGapBetween(target);
        int xGap = this.xGapBetween(target);

        return Direction.findDirection(xGap, yGap);
    }

    public boolean isColumn(Column column) {
        return this.x == column;
    }

    @Override
    public int compareTo(Position o) {
        if (y.isBiggerThan(o.y)) {
            return -1;
        }
        if (y == o.y && x.isSmallerThan(o.x)) {
            return -1;
        }
        return 1;
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
}
