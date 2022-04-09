package chess.domain.board;

import chess.domain.piece.Direction;
import java.util.Objects;

public class Position {

    private static final String WRONG_POSITION = "올바르지 않은 위치 입력입니다.";
    private static final int POSITION_ARGUMENT_LENGTH = 2;
    private static final int ROW_POSITION_FROM = 1;
    private static final int ROW_POSITION_TO = 2;
    private static final int COLUMN_POSITION_FROM = 0;
    private static final int COLUMN_POSITION_TO = 1;

    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(final String rawPosition) {
        if (rawPosition.length() != POSITION_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException(WRONG_POSITION);
        }
        final Row row = Row.from(rawPosition.substring(ROW_POSITION_FROM, ROW_POSITION_TO));
        final Column column = Column.from(rawPosition.substring(COLUMN_POSITION_FROM, COLUMN_POSITION_TO));
        return new Position(column, row);
    }

    public int calculateRowDifference(final Position target) {
        return row.calculateDifference(target.row);
    }

    public int calculateColumnDifference(final Position target) {
        return column.calculateDifference(target.column);
    }

    public Position move(final Direction direction) {
        return new Position(column.move(direction.getColumn()), row.move(direction.getRow()));
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

