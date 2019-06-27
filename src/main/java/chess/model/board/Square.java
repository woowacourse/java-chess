package chess.model.board;

import java.util.Objects;
import java.util.Optional;
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

    private static Optional<Square> getDiagonalOneNeighbor(
            final Square square,
            final Function<Square, Optional<Square>> getHorizontal,
            final Function<Square, Optional<Square>> getVertical
    ) {
        return getHorizontal.apply(square).flatMap(getVertical);
    }

    private static Optional<Square> getHorizontalOneNeighbor(final Square square, final int prevOrNext) {
        try {
            final Column column = square.getColumn();
            final Column newColumn = Column.getByIndex(column.getColumnIndex() + prevOrNext);
            return Optional.of(new Square(newColumn, square.getRow()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<Square> getVerticalOneNeighbor(final Square square, final int prevOrNext) {
        try {
            final Row row = square.getRow();
            final Row newRow = Row.getByIndex(row.getRowIndex() + prevOrNext);
            return Optional.of(new Square(square.getColumn(), newRow));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Square> getLeftOneNeighbor(final Square square) {
        return getHorizontalOneNeighbor(square, PREV);
    }

    public static Optional<Square> getRightOneNeighbor(final Square square) {
        return getHorizontalOneNeighbor(square, NEXT);
    }

    public static Optional<Square> getUpOneNeighbor(final Square square) {
        return getVerticalOneNeighbor(square, NEXT);
    }

    public static Optional<Square> getDownOneNeighbor(final Square square) {
        return getVerticalOneNeighbor(square, PREV);
    }

    public static Optional<Square> getUpperLeftOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getLeftOneNeighbor, Square::getUpOneNeighbor);
    }

    public static Optional<Square> getUpperRightOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getRightOneNeighbor, Square::getUpOneNeighbor);
    }

    public static Optional<Square> getLowerLeftOneNeighbor(final Square square) {
        return getDiagonalOneNeighbor(square, Square::getLeftOneNeighbor, Square::getDownOneNeighbor);
    }

    public static Optional<Square> getLowerRightOneNeighbor(final Square square) {
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
        return column.getColumnName().toLowerCase() + row.getRowName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
