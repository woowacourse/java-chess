package chess.domain;

import java.util.Objects;

public class Position {

    private static final int FIRST_COLUMN = 'a';
    private static final int REVERSE_ROW_INDEX = 7;

    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(String input) {
        this(
            REVERSE_ROW_INDEX - (Integer.parseInt(String.valueOf(input.charAt(1))) - 1),
            input.charAt(0) - FIRST_COLUMN
        );
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
