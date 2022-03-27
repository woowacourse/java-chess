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
        if (rawPosition.length() != POSITION_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException(WRONG_POSITION);
        }
        Row row = Row.from(rawPosition.substring(ROW_POSITION_FROM, ROW_POSITION_TO));
        Column column = Column.valueOf(rawPosition.substring(COLUMN_POSITION_FROM, COLUMN_POSITION_TO));
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

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Position)) {
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
