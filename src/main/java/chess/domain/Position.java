package chess.domain;

import java.util.Objects;

public class Position {
    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String position) {
        Row row=Row.valueOf(position.substring(0, 1));
        Column column = Column.valueOf(position.substring(1));

        return new Position(row, column);
    }
    public Row getRow(){
        return row;
    }
    public Column getColumn(){
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
}
