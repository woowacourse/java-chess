package chess.location;

import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
    private final Row row;
    private final Col col;

    public Location(final Row row, final Col col) {
        this.row = row;
        this.col = col;
    }

    public Location(final int row, final char col) {
        this(Row.of(row), Col.of(col));
    }

    public Location moveTo(final int row, final char col) {
        return new Location(row, col);
    }

    public Location calculateNextLocation(Location destination, int weight) {
        int rowWeight = weight;
        int colWeight = weight;

        if (row.is(destination.row)) {
            rowWeight = 0;
        }
        if (col.is(destination.col)) {
            colWeight = 0;
        }
        if (row.isHigherThan(destination.row)) {
            rowWeight = -1 * rowWeight;
        }
        if (col.isHigherThan(destination.col)) {
            colWeight = -1 * colWeight;
        }

        return new Location(row.plus(rowWeight), col.plus(colWeight));
    }

    public boolean isSameRow(Row row) {
        return row.is(this.row);
    }

    public boolean isSameRow(Location location) {
        return row.is(location.row);
    }

    public boolean isSameCol(Location after) {
        return col.is(after.col);
    }

    public boolean isSame(Col col) {
        return this.col.is(col);
    }

    public Location plusRowBy(int value) {
        return new Location(row.plus(value), col);
    }

    public Location plusBy(int rowValue, int colValue) {
        return new Location(row.plus(rowValue), col.plus(colValue));
    }

    public int getRowValue() {
        return row.getValue();
    }

    public char getColValue() {
        return col.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return row.is(location.row) &&
                col.is(location.col);
    }

    @Override
    public String toString() {
        return col.getValue() + "_" + row.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

