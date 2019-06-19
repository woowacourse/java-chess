package chess.model.board;

import java.util.ArrayList;
import java.util.List;
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

    Column getColumn() {
        return column;
    }

    Row getRow() {
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
        final Column column = square.getColumn();
        if (column.isFirst() || column.isLast()) {
            return null;
        }
        final int index = column.getColumnIndex();
        final Column newColumn = Column.getByIndex(index + prevOrNext);
        return new Square(newColumn, square.getRow());
    }

    private static Square getVerticalOneNeighbor(final Square square, final int prevOrNext) {
        final Row row = square.getRow();
        if (row.isFirst() || row.isLast()) {
            return null;
        }
        final int index = row.getRowIndex();
        final Row newRow = Row.getByIndex(index + prevOrNext);
        return new Square(square.getColumn(), newRow);
    }

    private static List<Square> getNeighbors(final Square square, final Function<Square, Square> getDirection) {
        final List<Square> result = new ArrayList<>();
        Square nextSquare = getDirection.apply(square);
        while (nextSquare != null) {
            result.add(nextSquare);
            nextSquare = getDirection.apply(nextSquare);
        }
        return result;
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

    public static List<Square> getLeftNeighbors(final Square square) {
        return getNeighbors(square, Square::getLeftOneNeighbor);
    }

    public static List<Square> getRightNeighbors(final Square square) {
        return getNeighbors(square, Square::getRightOneNeighbor);
    }

    public static List<Square> getUpNeighbors(final Square square) {
        return getNeighbors(square, Square::getUpOneNeighbor);
    }

    public static List<Square> getDownNeighbors(final Square square) {
        return getNeighbors(square, Square::getDownOneNeighbor);
    }

    public static List<Square> getUpperLeftNeighbors(final Square square) {
        return getNeighbors(square, Square::getUpperLeftOneNeighbor);
    }

    public static List<Square> getUpperRightNeighbors(final Square square) {
        return getNeighbors(square, Square::getUpperRightOneNeighbor);
    }

    public static List<Square> getLowerLeftNeighbors(final Square square) {
        return getNeighbors(square, Square::getLowerLeftOneNeighbor);
    }

    public static List<Square> getLowerRightNeighbors(final Square square) {
        return getNeighbors(square, Square::getLowerRightOneNeighbor);
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
