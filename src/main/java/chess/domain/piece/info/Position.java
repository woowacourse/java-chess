package chess.domain.piece.info;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
    private static final String POSITION_RANGE_ERROR = "[ERROR] 올바른 체스판 범위가 아닙니다.";
    private static final String ROW = "abcdefgh";
    private static final String COL = "87654321";
    private static final char BLACK_PAWN_FIRST_COL = '7';
    private static final char WHITE_PAWN_FIRST_COL = '2';
    public static final List<Position> POSITIONS;

    static {
        POSITIONS = COL.chars()
                .mapToObj(col -> (char) col)
                .flatMap(col -> ROW.chars()
                        .mapToObj(row -> new Position((char) row, col)))
                .collect(Collectors.toList());
    }

    private final char x;
    private final char y;

    private Position(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(char x, char y) {
        return POSITIONS.stream()
                .filter(position -> position.x == x && position.y == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(POSITION_RANGE_ERROR));
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

    public int subtractY(Position position) {
        return this.y - position.y;
    }

    public int subtractX(Position position) {
        return this.x - position.x;
    }

    public boolean isDiagonal(Position position) {
        return Math.abs(subtractX(position)) == Math.abs(subtractY(position));
    }

    public boolean isCross(Position position) {
        return (getX() == position.getX()) || (getY() == position.getY());
    }

    public boolean isUp(Position position) {
        return subtractX(position) == 0 && subtractY(position) < 0;
    }

    public boolean isDown(Position position) {
        return subtractX(position) == 0 && subtractY(position) > 0;
    }

    public boolean isRight(Position position) {
        return subtractX(position) < 0 && subtractY(position) == 0;
    }

    public boolean isLeft(Position position) {
        return subtractX(position) > 0 && subtractY(position) == 0;
    }

    public boolean isUpRight(Position position) {
        return subtractX(position) < 0 && subtractY(position) < 0;
    }

    public boolean isUpLeft(Position position) {
        return subtractX(position) > 0 && subtractY(position) < 0;
    }

    public boolean isDownRight(Position position) {
        return subtractX(position) < 0 && subtractY(position) > 0;
    }

    public boolean isDownLeft(Position position) {
        return subtractX(position) > 0 && subtractY(position) > 0;
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

    @Override
    public String toString() {
        return String.valueOf(x)+y;
    }
}
