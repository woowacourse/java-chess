package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    public static final Position EMPTY = new Position('0', '0');
    public static final List<Position> POSITIONS;
    public static final int CHANGE_LINE_POINT = 8;
    public static final String Xs = "abcdefgh";
    private static final String Ys = "87654321";

    static {
        POSITIONS = new ArrayList<>();
        for (int i = 0; i < CHANGE_LINE_POINT; i++) {
            for (int j = 0; j < CHANGE_LINE_POINT; j++) {
                POSITIONS.add(new Position(Xs.charAt(j), Ys.charAt(i)));
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
        return of(xy.charAt(0), xy.charAt(1));
    }

    public static Position of(char x, char y) {
        return POSITIONS.stream()
                .filter(position ->
                        position.x == x && position.y == y)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 올바른 체스판 범위가 아닙니다."));
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

    public boolean sameX(char anotherX) {
        return x == anotherX;
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
