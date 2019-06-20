package chess.model.board;

import java.util.Objects;
import java.util.function.Function;

public class Square {
    private final static int PREV = -1;
    private final static int NEXT = 1;

    private final Column column;
    private final Row row;

    public Square(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    private static Square getDiagonalOneNeighbor(final Square square, final Function<Square, Square> getHorizontal, final Function<Square, Square> getVertical) {
        final Square horizontal = getHorizontal.apply(square);
        if (horizontal == null) {
            return null;
        }
        return getVertical.apply(horizontal);
    }

    private static Square getHorizontalOneNeighbor(final Square square, final int prevOrNext) {
        try {
            final Column column = square.getColumn();
            final Column newColumn = Column.getByIndex(column.getColumnIndex() + prevOrNext);
            return new Square(newColumn, square.getRow());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private static Square getVerticalOneNeighbor(final Square square, final int prevOrNext) {
        try {
            final Row row = square.getRow();
            final Row newRow = Row.getByIndex(row.getRowIndex() + prevOrNext);
            return new Square(square.getColumn(), newRow);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static Square getLeftOneNeighbor(final Square square) {
        return getHorizontalOneNeighbor(square, PREV);
    }

    public static Square getRightOneNeighbor(final Square square) {
        return getHorizontalOneNeighbor(square, NEXT);
    }

    public static Square getUpOneNeighbor(final Square square) {
        return getVerticalOneNeighbor(square, NEXT);
    }

    public static Square getDownOneNeighbor(final Square square) {
        return getVerticalOneNeighbor(square, PREV);
    }

    public static Square getUpperLeftOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getLeftOneNeighbor, Square::getUpOneNeighbor);
    }

    public static Square getUpperRightOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getRightOneNeighbor, Square::getUpOneNeighbor);
    }

    public static Square getLowerLeftOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getLeftOneNeighbor, Square::getDownOneNeighbor);
    }

    public static Square getLowerRightOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getRightOneNeighbor, Square::getDownOneNeighbor);
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (!(another instanceof Square)) return false;
        final Square square = (Square) another;
        return (column == square.column) && (row == square.row);
    }

    @Override
    public String toString() {
        return column.toString() + ", " + row.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
