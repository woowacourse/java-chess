package domain.path.location;

import java.util.Objects;
import domain.path.direction.Direction;

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
