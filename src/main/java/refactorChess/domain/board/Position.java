package refactorChess.domain.board;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITIONS = new HashMap<>();

    static {
        for (int column = 0; column < 8; column++) {
            for (int row = 0; row < 8; row++) {
                POSITIONS.put(getKey(row, column), new Position(row, column));
            }
        }
    }

    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    private static String getKey(int row, int column) {
        return (char) ('a' + row) + String.valueOf(1 + column);
    }

    public static Position valueOf(String value) {
        if (!Position.POSITIONS.containsKey(value.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException("범위를 벗어난 값 입니다.");
        }

        return POSITIONS.get(value.toLowerCase(Locale.ROOT));
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
