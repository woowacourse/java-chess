package domain;

import domain.type.Direction;
import java.util.Objects;

public class Location {

    private static final int MAX_COL = 8;
    private static final int MIN_COL = 1;
    private static final int MAX_ROW = 8;
    private static final int MIN_ROW = 1;
    private static final String INVALID_LOCATION_ERROR_MESSAGE = "해당 위치는 존재하지 않습니다.";
    private final int column;
    private final int row;

    private Location(final int column, final int row) {
        validate(column, row);
        this.column = column;
        this.row = row;
    }

    public static Location of(final int column, final int row) {
        return new Location(column, row);
    }

    private void validate(final int column, final int row) {
        if (column < MIN_COL || column > MAX_COL
            || row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(INVALID_LOCATION_ERROR_MESSAGE);
        }
    }

    public boolean isSameCol(final Location location) {
        if (column == location.getColumn()) {
            return row != location.getRow();
        }
        return false;
    }

    public boolean isSameRow(final Location location) {
        if (row == location.getRow()) {
            return column != location.getColumn();
        }
        return false;
    }

    public boolean isSameLine(final Location location) {
        return isSameCol(location) || isSameRow(location);
    }

    public boolean isDiagonal(final Location location) {
        int colDifference = Math.abs(column - location.getColumn());
        int rowDifference = Math.abs(row - location.getRow());
        return colDifference == rowDifference;
    }

    public boolean isHigherThan(Location location) {
        return this.row > location.getRow();
    }

    public boolean isRightThan(Location location) {
        return this.column > location.getColumn();
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Location addDirectionOnce(final Direction direction) {
        return Location.of(column + direction.getColumnDiff(), row + direction.getRowDiff());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Location location = (Location) o;
        return getColumn() == location.getColumn() && getRow() == location.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getRow());
    }
}
