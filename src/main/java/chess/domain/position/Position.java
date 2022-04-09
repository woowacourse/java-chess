package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Position {
    private static final Map<String, Position> POSITIONS = new HashMap<>();
    private final static int POSITION_ROW_INDEX = 1;
    private final static int POSITION_COLUMN_INDEX = 0;

    private final Column col;
    private final Row row;

    public String getPositionToString() {
        return col.getSymbol() + row.getSymbol();
    }

    public static Position of(Column col, Row row) throws RuntimeException {
        String position = col.getSymbol() + row.getSymbol();
        return getPosition(position);
    }

    public static Position from(String position) throws RuntimeException {
        return getPosition(position);
    }

    private static Position getPosition(String position) {
        if (!POSITIONS.containsKey(position)) {
            Column column = Column.find(position.charAt(POSITION_COLUMN_INDEX));
            Row row = Row.find(position.charAt(POSITION_ROW_INDEX));
            POSITIONS.put(position, new Position(column, row));
        }
        return POSITIONS.get(position);
    }

    Position(Column col, Row row) {
        this.col = col;
        this.row = row;
    }

    public boolean matchPosition(Position position) {
        return this.equals(position);
    }

    public int getRowDifference(Row row) {
        return this.row.getDifference(row);
    }

    public int getColDifference(Column col) {
        return this.col.getDifference(col);
    }

    public Position plusDirection(Direction direction) {
        Column column = this.col.plusColumn(direction.getXDegree());
        Row row = this.row.plusRow(direction.getYDegree());
        String position = column.getSymbol() + row.getSymbol();
        return Position.from(position);
    }

    public Column getCol() {
        return col;
    }

    public Row getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return col == position.col && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
