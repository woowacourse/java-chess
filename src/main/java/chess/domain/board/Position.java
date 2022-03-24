package chess.domain.board;

import java.util.Objects;

import chess.domain.piece.Direction;

public class Position {

    private final Row row;
    private final Column column;

    public Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public Position(String rowString, String columnString) {
        this(Row.from(rowString), Column.valueOf(columnString));
    }

    public static Position from(String rawPosition) {
        if (rawPosition.length() != 2) {
            throw new IllegalArgumentException("올바르지 않은 위치 입력입니다.");
        }
        Row row = Row.from(rawPosition.substring(1, 2));
        Column column = Column.valueOf(rawPosition.substring(0, 1));
        return new Position(row, column);
    }

    public int calculateRowDifference(final Position target) {
        return row.calculateDifference(target.row);
    }

    public int calculateColumnDifference(final Position target) {
        return column.calculateDifference(target.column);
    }

    public Position move(final Direction direction) {
        return new Position(row.move(direction.getRow()), column.move(direction.getColumn()));
    }

    public Row getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Position)) {
            return false;
        }
        Position position = (Position)o;
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
