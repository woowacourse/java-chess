package chess.domain.piece.info;

import java.util.*;

public class Position {
    public final static String ROW = "abcdefgh";
    private final static String COL = "87654321";
    public static final Position EMPTY = new Position('0', '0');
    public final static Map<String, Position> POSITIONS;

    static {
        POSITIONS = new LinkedHashMap<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POSITIONS.put(String.valueOf(ROW.charAt(j)) + COL.charAt(i),
                        new Position(ROW.charAt(j), COL.charAt(i)));
            }
        }
    }

    private final char x;
    private final char y;

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(char x, char y) {
        validatePosition(x, y);
        return POSITIONS.get(String.valueOf(x) + y);
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

    public boolean isDiagonal(Position target) {
        return Math.abs(subtractX(target)) == Math.abs(subtractY(target));
    }

    public boolean isCross(Position target) {
        return (this.getX() == target.getX()) || (this.getY() == target.getY());
    }

    public boolean isFirstTurnIfPawn(Color color) {
        if (color.isSame(Color.BLACK)) {
            return y == '7';
        }
        if (color.isSame(Color.WHITE)) {
            return y == '2';
        }
        return false;
    }
}
