package chess.domain;

import java.util.*;

public final class Position {
    private static final Map<String, Position> positionMap = new HashMap<>();

    static {
        final List<String> alphabets = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        final List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");

        alphabets.forEach(alphabet -> numbers.forEach(number ->
                positionMap.put(alphabet + number, new Position(alphabets.indexOf(alphabet), numbers.indexOf(number)))));
    }

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final String input) {
        if (positionMap.containsKey(input)) {
            return positionMap.get(input);
        }
        throw new IllegalArgumentException("체스판에 없는 칸입니다");
    }

    public Position moveXandY(final int xDistance, final int yDistance) {
        return new Position(x + xDistance, y + yDistance);
    }

    public boolean checkStraight(final Position destination) {
        final boolean xSame = (this.x == destination.x);
        final boolean ySame = (this.y == destination.y);
        return xSame || ySame;
    }

    public boolean checkDiagonal(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        return xDiff == yDiff;
    }

    public boolean checkAdjacentFourWay(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        return (xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1);
    }

    public boolean checkAdjacentEightWay(final Position destination) {
        return checkAdjacentFourWay(destination) || checkAdjacentDiagonalToDirection(destination, 1)
                || checkAdjacentDiagonalToDirection(destination, -1);
    }

    public boolean checkAdjacentDiagonalToDirection(final Position destination, final int direction) {
        int xDiff = Math.abs(destination.x - this.x);
        int yDiff = destination.y - this.y;
        return xDiff == 1 && yDiff == direction;
    }

    public boolean checkKnightMoveRule(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        return ((xDiff == 1 && yDiff == 2) || (xDiff == 2 && yDiff == 1));
    }

    public boolean checkSameColumn(int x) {
        return this.x == x;
    }

    public boolean isEndRank() {
        return this.y == 0 || this.y == 7;
    }

    public List<Position> generateDiagonalPath(final Position destination) {
        final int xDiff = (destination.x - this.x);
        final int yDiff = (destination.y - this.y);
        return generateDiagonalPathByDiff(destination, xDiff, yDiff);
    }

    private List<Position> generateDiagonalPathByDiff(final Position destination, final int xDiff, final int yDiff) {
        if (xDiff > 0 && yDiff > 0) {
            return generatePathByDirection(destination, 1, 1);
        }
        if (xDiff > 0 && yDiff < 0) {
            return generatePathByDirection(destination, 1, -1);
        }
        if (xDiff < 0 && yDiff > 0) {
            return generatePathByDirection(destination, -1, 1);
        }
        return generatePathByDirection(destination, -1, -1);
    }

    public List<Position> generateStraightPath(final Position destination) {
        final int xDiff = destination.x - this.x;
        final int yDiff = destination.y - this.y;
        return generateStraightPathByDiff(destination, xDiff, yDiff);
    }

    private List<Position> generateStraightPathByDiff(Position destination, int xDiff, int yDiff) {
        if (xDiff > 0 && yDiff == 0) {
            return generatePathByDirection(destination, 1, 0);
        }
        if (xDiff < 0 && yDiff == 0) {
            return generatePathByDirection(destination, -1, 0);
        }
        if (xDiff == 0 && yDiff > 0) {
            return generatePathByDirection(destination, 0, 1);
        }
        return generatePathByDirection(destination, 0, -1);
    }

    private List<Position> generatePathByDirection(final Position destination, final int xDistance, final int yDistance) {
        final List<Position> rightUpPath = new ArrayList<>();
        Position path = this.moveXandY(xDistance, yDistance);
        while (!path.equals(destination)) {
            rightUpPath.add(path);
            path = path.moveXandY(xDistance, yDistance);
        }
        return rightUpPath;
    }

    public String getPositionInitial() {
        return positionMap.keySet().stream()
                .filter(initial -> positionMap.get(initial).equals(this))
                .findFirst()
                .orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
