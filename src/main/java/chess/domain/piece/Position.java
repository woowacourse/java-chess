package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final static String ROW = "abcdefgh";
    private final static String COL = "12345678";
    public static final Position EMPTY = new Position('0', '0');
    public final static List<Position> POSITIONS;

    static {
        POSITIONS = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POSITIONS.add(Position.of(ROW.charAt(i), COL.charAt(j)));
            }
        }
    }

    private char x;
    private char y;

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(char x, char y) {
        validatePosition(x, y);
        return new Position(x, y);
    }

    private static void validatePosition(char x, char y) {
        if (ROW.contains(String.valueOf(x)) && COL.contains(String.valueOf(y))) {
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

    public int subtractY(Position anotherPosition) {
        return this.y - anotherPosition.y;
    }

    public int subtractX(Position anotherPosition) {
        return this.x - anotherPosition.x;
    }

    public boolean isSubtractYPositive(Position anotherPosition) {
        return this.y > anotherPosition.y;
    }

    public boolean isSubtractXPositive(Position anotherPosition) {
        return this.x > anotherPosition.x;
    }
}
