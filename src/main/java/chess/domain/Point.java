package chess.domain;

import java.util.Objects;

public class Point {
    public static final char X_START = 'a';
    public static final char X_END = 'h';
    public static final char Y_START = '1';
    public static final char Y_END = '8';
    private final int positionX;
    private final int positionY;

    public Point(String position) {
        this(position.charAt(0), position.charAt(1));
    }

    public Point(char positionX, char positionY) {
        checkPositionX(positionX);
        checkPositionY(positionY);
        this.positionX = changeTypeX(positionX);
        this.positionY = changeTypeY(positionY);
    }

    private void checkPositionX(char positionX) {
        if (positionX < X_START || positionX > X_END) {
            throw new IllegalArgumentException("Y 좌표는 " + X_START + "부터 " + X_END + "까지만 허용합니다.");
        }
    }

    private void checkPositionY(char positionY) {
        if (positionY < Y_START || positionY > Y_END) {
            throw new IllegalArgumentException("Y 좌표는 " + Y_START + "부터 " + Y_END + "까지만 허용합니다.");
        }
    }

    private int changeTypeX(char x){
        return x - X_START;
    }

    private int changeTypeY(char y){ return y - Y_START; }

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
