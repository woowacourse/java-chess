package domain.path.location;

import domain.path.direction.Direction;
import java.util.Objects;

public final class Location {

    private final Column column;
    private final Row row;

    private Location(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Location of(final Row row, final Column column) {
        return new Location(row, column);
    }

    public boolean isSameCol(final Location location) {
        if (column.getValue() == location.getCol()) {
            return row.getValue() != location.getRow();
        }
        return false;
    }

    public boolean isSameRow(final Location location) {
        if (row.getValue() == location.getRow()) {
            return column.getValue() != location.getCol();
        }
        return false;
    }

    public boolean isDiagonal(final Location location) {
        final int columnDifference = Math.abs(column.getValue() - location.getCol());
        final int rowDifference = Math.abs(row.getValue() - location.getRow());
        return columnDifference == rowDifference;
    }

    public boolean isHigherThan(final Location location) {
        return this.row.getValue() > location.getRow();
    }

    public boolean isRightThan(final Location location) {
        return this.column.getValue() > location.getCol();
    }

    public Location addDirectionOnce(final Direction direction) {
        return Location.of(
            row.add(direction.getRowDiff()),
            column.add(direction.getColDiff())
        );
    }

    public int getCol() {
        return column.getValue();
    }

    public int getRow() {
        return row.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return column.equals(location.column) && row.equals(location.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Location{" +
            "column=" + column +
            ", row=" + row +
            '}';
    }
}
