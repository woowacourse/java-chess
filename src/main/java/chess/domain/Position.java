package chess.domain;

import java.util.*;

public class Position {
    private static final Map<String, Position> positionMap = new HashMap<>();

    static {
        final List<String> alphabets = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        final List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");

        alphabets.stream().flatMap(alphabet -> numbers.stream().map(number -> alphabet + number))
                .forEach(positionStr -> positionMap.put(
                        positionStr,
                        new Position(alphabets.indexOf(positionStr.substring(0, 1)),
                                numbers.indexOf(positionStr.substring(1, 2)))));
    }

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final String input) {
        return positionMap.get(input);
    }

    public String getKey() {
        for (Map.Entry<String, Position> entry : positionMap.entrySet())
            if (this.equals(entry.getValue())) {
                return entry.getKey();
            }

        return "";
    }

    public Position moveXandY(final int xDistance, final int yDistance) {
        return new Position(x + xDistance, y + yDistance);
    }

    public boolean checkDiagonalToDirection(final Position destination, final int direction) {
        int xDiff = Math.abs(destination.x - this.x);
        int yDiff = destination.y - this.y;
        return xDiff == 1 && yDiff == direction;
    }

    public boolean checkAdjacentFourWay(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        return (xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1);
    }

    public boolean checkAdjacentEightWay(final Position destination) {
        return checkAdjacentFourWay(destination) || checkDiagonalToDirection(destination, 1)
                || checkDiagonalToDirection(destination, -1);
    }

    public boolean checkDiagonalRule(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        return xDiff == yDiff;
    }

    public boolean checkStraightRule(final Position destination) {
        final boolean xSame = (this.x == destination.x);
        final boolean ySame = (this.y == destination.y);
        return xSame || ySame;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
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