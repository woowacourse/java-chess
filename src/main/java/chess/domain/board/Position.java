package chess.domain.board;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Col col;

    private Position(final Row row, final Col col) {
        this.row = row;
        this.col = col;
    }

    public static Position from(final String input) {
        Row row = Row.fromByInput(input.charAt(1));
        Col col = Col.fromByInput(input.charAt(0));
        return new Position(row, col);
    }

    public static Position of(final Row row, final Col col) {
        return new Position(row, col);
    }

    public boolean isPlacePositionAtFirst() {
        return this.row.getIndexOfRow() >= '3' && this.row.getIndexOfRow() <= '6';
    }

    public boolean isLowerPawnPositionAtFirst() {
        return this.row.getIndexOfRow() == '2';
    }

    public boolean isUpperPawnPositionAtFirst() {
        return this.row.getIndexOfRow() == '7';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
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
