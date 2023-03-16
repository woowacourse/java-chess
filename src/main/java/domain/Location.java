package domain;

import java.util.Objects;

public class Location {

    private static final int MAX_COL = 7;
    private static final int MIN_COL = 0;
    private static final int MAX_ROW = 7;
    private static final int MIN_ROW = 0;
    private static final String INVALID_LOCATION_ERROR_MESSAGE = "해당 위치는 존재하지 않습니다.";
    private final int col;
    private final int row;

    private Location(final int col, final int row) {
        validate(col, row);
        this.col = col;
        this.row = row;
    }

    private void validate(final int col, final int row) {
        if (col < MIN_COL || col > MAX_COL
            || row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(INVALID_LOCATION_ERROR_MESSAGE);
        }
    }

    public static Location of(final int col, final int row) {
        return new Location(col, row);
    }

    public boolean isSameCol(final Location location) {
        if (col == location.getCol()) {
            return row != location.getRow();
        }
        return false;
    }

    public boolean isSameRow(final Location location) {
        if (row == location.getRow()) {
            return col != location.getCol();
        }
        return false;
    }

    public boolean isSameLine(final Location location) {
        return isSameCol(location) || isSameRow(location);
    }

    public boolean isDiagonal(final Location location) {
        int colDifference = Math.abs(col - location.getCol());
        int rowDifference = Math.abs(row - location.getRow());
        return colDifference == rowDifference;
    }

    public boolean isHigherThan(Location location) {
        return this.row > location.getRow();
    }

    public boolean isRightThan(Location location) {
        return this.col > location.getCol();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
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
        return getCol() == location.getCol() && getRow() == location.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCol(), getRow());
    }
}
