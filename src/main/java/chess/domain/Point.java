package chess.domain;

import java.util.Objects;

public class Point {
    private static final char X_START = 'a';
    private static final char X_END = 'h';
    private static final char Y_START = '1';
    private static final char Y_END = '8';
    private static final int EXPONENT = 2;
    private static final int X_INDEX = 0;
    private static final int Y_INDEX = 1;

    private final int positionX;
    private final int positionY;

    public Point(String position) {
        this(position.charAt(X_INDEX), position.charAt(Y_INDEX));
    }

    public Point(char positionX, char positionY) {
        checkPositionX(positionX);
        checkPositionY(positionY);
        this.positionX = changeTypeX(positionX);
        this.positionY = changeTypeY(positionY);
    }

    public Point(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    private void checkPositionX(char positionX) {
        if (positionX < X_START || positionX > X_END) {
            throw new IllegalArgumentException("X 좌표는 " + X_START + "부터 " + X_END + "까지만 허용합니다.");
        }
    }

    private void checkPositionY(char positionY) {
        if (positionY < Y_START || positionY > Y_END) {
            throw new IllegalArgumentException("Y 좌표는 " + Y_START + "부터 " + Y_END + "까지만 허용합니다.");
        }
    }

    private int changeTypeX(char x) {
        return x - X_START;
    }

    private int changeTypeY(char y) {
        return y - Y_START;
    }

    public int calDistance(Point end) {
        double result = square(subtractX(end)) + square(subtractY(end));
        return (int) result;
    }

    private double square(int value) {
        return Math.pow(value, EXPONENT);
    }

    private int subtractX(Point end) {
        return positionX - end.positionX;
    }

    private int subtractY(Point end) {
        return positionY - end.positionY;
    }

    public boolean isSameCoordinate(Point end) {
        if (calDistance(end) == 0) {
            return false;
        }
        return (positionX == end.positionX || positionY == end.positionY);
    }

    public Point move(Direction direction) {
        Point directionPoint = direction.getPosition();
        char X = (char) (positionX + directionPoint.positionX + X_START);
        char Y = (char) (positionY + directionPoint.positionY + Y_START);
        return new Point(X, Y);
    }

    public Point makeVector(Point end) {
        int subX = subtractX(end);
        int subY = subtractY(end);
        return new Point(-subX,-subY);
    }

    int dotProduct(Point end) {
        return (positionX * end.positionX) + (positionY * end.positionY);
    }

    double calScalar() {
        return Math.sqrt(square(positionX) + square(positionY));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return positionX == point.positionX &&
                positionY == point.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
