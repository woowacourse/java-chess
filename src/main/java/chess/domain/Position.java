package chess.domain;

import java.util.*;

public class Position {
    private static final Map<String, Position> positionMap = new HashMap<>();

    static {
        String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                positionMap.put(alphabets[i] + numbers[j], new Position(i, j));
            }
        }
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

    public Position moveX(final int xDistance) {
        return new Position(x + xDistance, y);
    }

    public Position moveY(final int yDistance) {
        return new Position(x, y + yDistance);
    }

    public Position moveXandY(final int xDistance, final int yDistance) {
        return new Position(x + xDistance, y + yDistance);
    }

    public boolean checkDiagonalToDirection(final Position destination, final int direction) {
        int xDiff = Math.abs(destination.x - this.x);
        int yDiff = destination.y - this.y;
        if (xDiff == 1 && yDiff == direction) {
            return true;
        }
        return false;
    }

    public boolean checkAdjacentFourWay(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        if ((xDiff == 1 && yDiff == 0) || (xDiff == 0 && yDiff == 1)) {
            return true;
        }
        return false;
    }

    public boolean checkAdjacentEightWay(final Position destination) {
        return checkAdjacentFourWay(destination) || checkDiagonalToDirection(destination, 1)
                || checkDiagonalToDirection(destination, -1);
    }

    public boolean checkDiagonalRule(final Position destination) {
        final int xDiff = Math.abs(this.x - destination.x);
        final int yDiff = Math.abs(this.y - destination.y);
        if (xDiff == yDiff) {
            return true;
        }
        return false;
    }

    public boolean checkStraightRule(final Position destination) {
        if (this.x == destination.x && this.y != destination.y) {
            return true;
        }
        if (this.y == destination.y && this.x != destination.x) {
            return true;
        }
        return false;
    }

    public List<Position> generateDiagonalPath(final Position destination) {
        int xDiff = destination.x - this.x;
        int yDiff = destination.y - this.y;
        if (xDiff > 0 && yDiff > 0) {
            return generateRightUpPath(destination);
        } else if (xDiff > 0 && yDiff < 0) {
            return generateRightDownPath(destination);
        } else if (xDiff < 0 && yDiff > 0) {
            return generateLeftUpPath(destination);
        } else {
            return generateLeftDownPath(destination);
        }
    }

    public List<Position> generateStraightPath(final Position destination) {
        if (this.x == destination.getX() && this.y != destination.getY()) {
            return generateYaxisPath(destination);
        }

        return generateXaxisPath(destination);
    }


    private List<Position> generateXaxisPath(final Position destination) {
        int xDiff = destination.getX() - x;
        if (xDiff > 0) {
            return generateRightPath(destination);
        }

        return generateLeftPath(destination);
    }

    private List<Position> generateLeftPath(Position destination) {
        final List<Position> leftPath = new ArrayList<>();
        Position path = this.moveX(-1);
        while (!path.equals(destination)) {
            leftPath.add(path);
            path = path.moveX(-1);
        }
        return leftPath;
    }

    private List<Position> generateRightPath(Position destination) {
        final List<Position> rightPath = new ArrayList<>();
        Position path = this.moveX(1);
        while (!path.equals(destination)) {
            rightPath.add(path);
            path = path.moveX(1);
        }
        return rightPath;
    }


    private List<Position> generateYaxisPath(final Position destination) {
        int yDiff = destination.getY() - y;
        if (yDiff > 0) {
            return generateUpPath(destination);
        }

        return generateDownPath(destination);

    }

    private List<Position> generateDownPath(Position destination) {
        final List<Position> downPath = new ArrayList<>();
        Position path = this.moveY(-1);
        while (!path.equals(destination)) {
            downPath.add(path);
            path = path.moveY(-1);
        }
        return downPath;
    }

    private List<Position> generateUpPath(final Position destination) {
        final List<Position> upPath = new ArrayList<>();
        Position path = this.moveY(1);
        while (!path.equals(destination)) {
            upPath.add(path);
            path = path.moveY(1);
        }
        return upPath;
    }


    private List<Position> generateRightUpPath(final Position destination) {
        final List<Position> rightUpPath = new ArrayList<>();
        Position path = this.moveXandY(1, 1);
        while (!path.equals(destination)) {
            rightUpPath.add(path);
            path = path.moveXandY(1, 1);
        }
        return rightUpPath;
    }

    private List<Position> generateRightDownPath(final Position destination) {
        final List<Position> rightDownPath = new ArrayList<>();
        Position path = this.moveXandY(1, -1);
        while (!path.equals(destination)) {
            rightDownPath.add(path);
            path = path.moveXandY(1, -1);
        }
        return rightDownPath;
    }

    private List<Position> generateLeftUpPath(final Position destination) {
        final List<Position> leftUpPath = new ArrayList<>();
        Position path = this.moveXandY(-1, 1);
        while (!path.equals(destination)) {
            leftUpPath.add(path);
            path = path.moveXandY(-1, 1);
        }
        return leftUpPath;
    }

    private List<Position> generateLeftDownPath(final Position destination) {
        final List<Position> leftDownPath = new ArrayList<>();
        Position path = this.moveXandY(-1, -1);
        while (!path.equals(destination)) {
            leftDownPath.add(path);
            path = path.moveXandY(-1, -1);
        }
        return leftDownPath;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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