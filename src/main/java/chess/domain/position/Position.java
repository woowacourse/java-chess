package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.Objects;

public class Position {

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private Position(String column, String row) {
        this(Column.getColumn(column), Row.getRow(row));
    }

    public static Position of(String value) {
        return new Position(value.substring(0, 1), value.substring(1));
    }

    public static Position of(Column column, Row row) {
        return new Position(column, row);
    }

    public boolean isWhitePawnStartLine(){
        return row.equals(Row.TWO);
    }

    public boolean isBlackPawnStartLine(){
        return row.equals(Row.SEVEN);
    }

    public boolean isStraight(Position that){
        return columnGap(that) == 0 || rowGap(that) == 0;
    }

    public boolean isDiagonal(Position that){
        double slope = calculateSlopeWith(that);
        return slope == 1 || slope == -1;
    }

    public int columnGap(Position that){
        return column() - that.column();
    }

    public int rowGap(Position that){
        return row() - that.row();
    }

    public int row(){
        return row.getValue();
    }

    public int column(){
        return column.getValue();
    }

    public String name(){
        return this.row.getNumber() + this.column.getName();
    }

    public double calculateSlopeWith(Position that) {
        return rowGap(that) / columnGap(that);
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
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public Position moveTo(Direction direction) {
        Column newColumn = column.move(direction);
        Row newRow = row.move(direction);
        return Position.of(newColumn, newRow);
    }

}
