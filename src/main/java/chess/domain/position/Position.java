package chess.domain.position;

import chess.domain.chessPiece.Team;

import java.util.Objects;

public class Position {
    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String position) {
        Row row = Row.valueOf(position.substring(0, 1));
        Column column = Column.valueOf(position.substring(1));

        return new Position(row, column);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public Position move(int rowDirection, int columnDirection) {
        return new Position(row.update(rowDirection), column.update(columnDirection));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public int calculateRowDistance(Position target) {
        return Math.abs(row.getValue() - target.row.getValue());
    }

    public int calculateColumnDistance(Position target) {
        return Math.abs(column.getValue() - target.getColumn().getValue());
    }

    public int calculatePawnColumnDistance(Position target, Team team) {
        if (team.isWhite()) {
            return target.column.subtractColumn(column);
        }
        return column.subtractColumn(target.column);
    }

    public boolean isSameRow(Position target) {
        return this.row == target.row;
    }

    public boolean isSameColumn(Position target) {
        return this.column == target.column;
    }

    public boolean isDifferentRow(Position target) {
        return !isSameRow(target);
    }

    public boolean isDifferentColumn(Position target) {
        return !isSameColumn(target);
    }


    public int compareRow(Position target) {
        return row.compare(target.row);
    }

    public int compareColumn(Position target) {
        return column.compare(target.column);
    }
}
