package chess.location;

import static java.lang.Math.*;

import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
    private final Row row;
    private final Col col;

    public Location(final int row, final char col) {
        this.row = Row.of(row);
        this.col = Col.of(col);
    }

    public Location(final Row row, final Col col) {
        this.row = row;
        this.col = col;
    }

    public Location moveTo(final int row, final char col) {
        return new Location(row, col);
    }

    public boolean isDiagonal(Location destination) {
        return abs(row.getValue() - destination.row.getValue())
                == abs(col.getValue() - destination.col.getValue());
    }

    public boolean isStraight(Location destination) {
        return row.is(destination.row) || isVertical(destination);
    }

    // 폰의 대각선위치인지 확인하는 메서드
    public boolean isForwardDiagonal(Location after, int value) {
        Col leftCol = col.minus(1);
        Col rightCol = col.plus(1);

        return row.plus(value).is(after.row)
                && (leftCol.is(after.col) || rightCol.is(after.col));
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

    private boolean isVertical(Location destination) {
        return col.is(destination.col);
    }

    public boolean isSameRow(Row row) {
        return row.is(row);
    }

    public boolean isSameRow(Location location) {
        return row.is(location.row);
    }

    public boolean isSameCol(Location after) {
        return col.is(after.col);
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

