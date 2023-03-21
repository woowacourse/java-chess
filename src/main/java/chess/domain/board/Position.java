package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final char EMPTY_PLACE_START_INDEX = '3';
    private static final char EMPTY_PLACE_END_INDEX = '6';
    private static final char LOWER_PAWN_INDEX = '2';
    private static final char UPPER_PAWN_INDEX = '7';

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(final String input) {
        Row row = Row.fromByInput(input.charAt(1));
        Column column = Column.fromByInput(input.charAt(0));
        return new Position(row, column);
    }

    public static Position of(final Row row, final Column column) {
        return new Position(row, column);
    }

    public boolean isPlacePositionAtFirst() {
        return EMPTY_PLACE_START_INDEX <= this.row.getIndexOfRow() && this.row.getIndexOfRow() <= EMPTY_PLACE_END_INDEX;
    }

    public boolean isLowerPawnPositionAtFirst() {
        return this.row.getIndexOfRow() == LOWER_PAWN_INDEX;
    }

    public boolean isUpperPawnPositionAtFirst() {
        return this.row.getIndexOfRow() == UPPER_PAWN_INDEX;
    }

    public char getCol() {
        return column.getIndexOfCol();
    }

    public char getRow() {
        return row.getIndexOfRow();
    }

    public static List<Position> getAllPositionsByColumn(final Column column) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(row, column))
                .collect(Collectors.toList());
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
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return String.valueOf(column.getIndexOfCol()) + row.getIndexOfRow();
    }
}
