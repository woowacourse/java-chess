package domain;

import domain.type.direction.PieceMoveDirection;
import java.util.Objects;

public final class Location {

    private static final int MAX_RANGE = 7;
    private static final int MIN_RANGE = 0;
    private static final String INVALID_LOCATION_ERROR_MESSAGE = "해당 위치는 존재하지 않습니다.";
    private final int col;
    private final int row;

    private Location(final int col, final int row) {
        validate(col, row);
        this.col = col;
        this.row = row;
    }

    private void validate(final int col, final int row) {
        if (col < MIN_RANGE || col > MAX_RANGE
            || row < MIN_RANGE || row > MAX_RANGE) {
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
        final int colDifference = Math.abs(col - location.getCol());
        final int rowDifference = Math.abs(row - location.getRow());
        return colDifference == rowDifference;
    }

    public boolean isHigherThan(final Location location) {
        return this.row > location.getRow();
    }

    public boolean isRightThan(final Location location) {
        return this.col > location.getCol();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Location addDirectionOnce(final PieceMoveDirection direction) {
        return Location.of(col + direction.getColDiff(), row + direction.getRowDiff());
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

    @Override
    public String toString() {
        return "Location{" +
            "col=" + col +
            ", row=" + row +
            '}';
    }
}
