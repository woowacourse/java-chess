package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    public static final String Xs = "abcdefgh";
    private static final String Ys = "87654321";
    public static final Position EMPTY = new Position('0', '0');
    public static final List<Position> POSITIONS;

    static {
        POSITIONS = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POSITIONS.add(Position.of(Xs.charAt(j), Ys.charAt(i)));
            }
        }
    }

    private char x;
    private char y;

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(String xy) {
        validatePosition(xy.charAt(0), xy.charAt(1));
        return new Position(xy.charAt(0), xy.charAt(1));
    }

    public static Position of(char x, char y) {
        validatePosition(x, y);
        return new Position(x, y);
    }

    private static void validatePosition(char x, char y) {
        if (Xs.contains(String.valueOf(x)) && Ys.contains(String.valueOf(y))) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 올바른 체스판 범위가 아닙니다.");
    }

    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public int yDistance(Position anotherPosition) {
        return this.y - anotherPosition.y;
    }

    public int xDistance(Position anotherPosition) {
        return this.x - anotherPosition.x;
    }

    public boolean largeY(Position anotherPosition) {
        return this.y > anotherPosition.y;
    }

    public boolean largeX(Position anotherPosition) {
        return this.x > anotherPosition.x;
    }

    public boolean isDiagonal(Position target) {
        return Math.abs(xDistance(target)) == Math.abs(yDistance(target));
    }

    public boolean isCross(Position target) {
        return (this.x == target.x) || (this.y == target.y);
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
