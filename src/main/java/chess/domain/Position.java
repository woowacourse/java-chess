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
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("판에서 벗어난 좌표입니다.");
        }
        this.x = x;
        this.y = y;
    }

    public static Position of(final String input) {
        return positionMap.get(input);
    }

    public Position moveX(final int distance) {
        return new Position(x + distance, y);
    }

    public Position moveY(final int distance) {
        return new Position(x, y + distance);
    }

    public Position moveUp() {
        return new Position(x, y + 1);
    }

    public Position moveDown() {
        return new Position(x, y - 1);
    }

    public Position moveLeft() {
        return new Position(x - 1, y);
    }

    public Position moveRight() {
        return new Position(x + 1, y);
    }

    public Position moveLeftUp() {
        return new Position(x - 1, y + 1);
    }

    public Position moveLeftDown() {
        return new Position(x - 1, y - 1);
    }

    public Position moveRightUp() {
        return new Position(x + 1, y + 1);
    }

    public Position moveRightDown() {
        return new Position(x + 1, y - 1);
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
        Position path = this.moveLeft();
        while (!path.equals(destination)) {
            leftPath.add(path);
            path = path.moveLeft();
        }
        return leftPath;
    }

    private List<Position> generateRightPath(Position destination) {
        final List<Position> rightPath = new ArrayList<>();
        Position path = this.moveRight();
        while (!path.equals(destination)) {
            rightPath.add(path);
            path = path.moveRight();
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
        Position path = this.moveDown();
        while (!path.equals(destination)) {
            downPath.add(path);
            path = path.moveDown();
        }
        return downPath;
    }

    private List<Position> generateUpPath(final Position destination) {
        final List<Position> upPath = new ArrayList<>();
        Position path = this.moveUp();
        while (!path.equals(destination)) {
            upPath.add(path);
            path = path.moveUp();
        }
        return upPath;
    }


    private List<Position> generateRightUpPath(final Position destination) {
        final List<Position> rightUpPath = new ArrayList<>();
        Position path = this.moveRightUp();
        while (!path.equals(destination)) {
            rightUpPath.add(path);
            path = path.moveRightUp();
        }
        return rightUpPath;
    }

    private List<Position> generateRightDownPath(final Position destination) {
        final List<Position> rightDownPath = new ArrayList<>();
        Position path = this.moveRightDown();
        while (!path.equals(destination)) {
            rightDownPath.add(path);
            path = path.moveRightDown();
        }
        return rightDownPath;
    }

    private List<Position> generateLeftUpPath(final Position destination) {
        final List<Position> leftUpPath = new ArrayList<>();
        Position path = this.moveLeftUp();
        while (!path.equals(destination)) {
            leftUpPath.add(path);
            path = path.moveLeftUp();
        }
        return leftUpPath;
    }

    private List<Position> generateLeftDownPath(final Position destination) {
        final List<Position> leftDownPath = new ArrayList<>();
        Position path = this.moveLeftDown();
        while (!path.equals(destination)) {
            leftDownPath.add(path);
            path = path.moveLeftDown();
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