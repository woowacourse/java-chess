package chess.location;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
    private static final int KING_RANGE = 1;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PAWN_ROW = 7;

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

    public boolean isQueenRang(Location destination) {
        return isDiagonal(destination) || isStraight(destination);
    }

    public boolean isStraight(Location destination) {
        return row.is(destination.row) || isVertical(destination);
    }

    public boolean isInitialPawnLocation(boolean black) {
        if (black) {
            return isContainsInitialLocation(Row.of(BLACK_PAWN_ROW));
        }
        return isContainsInitialLocation(Row.of(WHITE_PAWN_ROW));
    }

    private boolean isContainsInitialLocation(Row row) {
        List<Location> pawnLocations = new ArrayList();
        for (Col col : Col.values()) {
            pawnLocations.add(
                    new Location(row, col)
            );
        }
        return pawnLocations.contains(this);
    }

    // 2칸 혹은 한 칸이동할 수 있다.
    public boolean isInitialPawnForwardRange(Location after, int value) {
        Row onceMovedRowByValue = row.plus(value);
        Row TwiceMovedRowByValue = row.plus(value * 2);
        boolean result = onceMovedRowByValue.is(after.row)
                || TwiceMovedRowByValue.is(after.row);

        return result && col == after.col;
    }

    public boolean isPawnForwardRange(Location after, int value) {
        Row movedRowByValue = row.plus(value);

        return movedRowByValue.is(after.row)
                && col.is(after.col);
    }

    // 폰의 대각선위치인지 확인하는 메서드
    public boolean isForwardDiagonal(Location after, int value) {
        Col leftCol = col.minus(1);
        Col rightCol = col.plus(1);

        return row.plus(value).is(after.row)
                && leftCol.is(after.col)
                || rightCol.is(after.col);
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

    public boolean is(int row) {
        return this.row.is(row);
    }

    public int getRow() {
        return row.getValue();
    }

    public char getCol() {
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
        return "[" + col + ", " + row + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

