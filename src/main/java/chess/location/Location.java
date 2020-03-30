package chess.location;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
    private static final int KING_RANGE = 1;

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

    public boolean isKingRange(Location destination) {
        boolean canRowMove =
                abs(row.getValue() - destination.row.getValue()) <= KING_RANGE;
        boolean canColMove =
                abs(col.getValue() - destination.col.getValue()) <= KING_RANGE;

        return canRowMove && canColMove;
    }

    public boolean isKnightRange(Location destination) {
        int[] dRow = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] dCol = {1, -1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < dRow.length; i++) {
            Row nx = row.plus(dRow[i]);
            Col ny = col.plus(dCol[i]);
            if (destination.row.is(nx) && destination.col.is(ny)) {
                return true;
            }
        }
        return false;
    }

    public boolean isQueenRange(Location destination) {
        return isDiagonal(destination) || isStraight(destination);
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

    public boolean isVertical(Location destination) {
        return col.is(destination.col);
    }

    public boolean isSameRow(int row) {
        return this.row.is(row);
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

    public Row getRow() {
        return row;
    }

    public int getRowValue() {
        return row.getValue();
    }

    public Col getCol() {
        return col;
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
        return "[" + col + ", " + row + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

