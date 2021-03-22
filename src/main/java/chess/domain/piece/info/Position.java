package chess.domain.piece.info;

import java.util.*;

public class Position {
    private static final String POSITION_RANGE_ERROR = "[ERROR] 올바른 체스판 범위가 아닙니다.";
    private static final String ROW = "abcdefgh";
    private static final String COL = "87654321";
    private static final char BLACK_PAWN_FIRST_COL = '7';
    private static final char WHITE_PAWN_FIRST_COL = '2';
    public static final Position EMPTY = new Position('0', '0');
    public static final Map<String, Position> POSITIONS;

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
        if (!ROW.contains(String.valueOf(x)) || !COL.contains(String.valueOf(y))) {
            throw new IllegalArgumentException(POSITION_RANGE_ERROR);
        }
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
            return y == BLACK_PAWN_FIRST_COL;
        }
        if (color.isSame(Color.WHITE)) {
            return y == WHITE_PAWN_FIRST_COL;
        }
        return false;
    }
}
