package chess.board;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
    private static final int KING_RANGE = 1;
    private static final int WHITE_PAWN_LOW = 2;
    private static final int BLACK_PAWN_LOW = 7;
    private final int row;
    private final char col;

    public Location(final int row, final char col) {
        this.row = row;
        this.col = col;
    }

    public Location moveTo(final int row, final char col) {
        return new Location(row, col);
    }

    public Location moveRowBy(final int rowValue) {
        return moveTo(this.row + rowValue, col);
    }

    public Location moveColBy(final int colValue) {
        return moveTo(row, (char) (this.col + colValue));
    }

    public boolean isDiagonal(Location destination) {
        return abs(row - destination.row) == abs(col - destination.col);
    }

    public boolean isKingRange(Location destination) {
        boolean rowFlag = abs(row - destination.row) <= KING_RANGE;
        boolean colFlag = abs(col - destination.col) <= KING_RANGE;

        return rowFlag && colFlag;
    }

    public boolean isKnightRange(Location destination) {
        int[] dx = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] dy = {1, -1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < dx.length; i++) {
            int nx = this.row + dx[i];
            int ny = this.col + dy[i];
            if (destination.row == nx && destination.col == ny) {
                return true;
            }
        }
        return false;
    }

    public boolean isQueenRang(Location destination) {
        return isDiagonal(destination) || isStraight(destination);
    }

    public boolean isStraight(Location destination) {
        return this.row == destination.row || isVertical(destination);
    }

    public boolean isInitialPawnLocation(boolean black) {
        Location[] whiteTeamInitialPawnLocations = {
                new Location(WHITE_PAWN_LOW, 'a'),
                new Location(WHITE_PAWN_LOW, 'b'),
                new Location(WHITE_PAWN_LOW, 'c'),
                new Location(WHITE_PAWN_LOW, 'd'),
                new Location(WHITE_PAWN_LOW, 'e'),
                new Location(WHITE_PAWN_LOW, 'f'),
                new Location(WHITE_PAWN_LOW, 'g'),
                new Location(WHITE_PAWN_LOW, 'h')
        };
        Location[] blackTeamInitialPawnLocations = {
                new Location(BLACK_PAWN_LOW, 'a'),
                new Location(BLACK_PAWN_LOW, 'b'),
                new Location(BLACK_PAWN_LOW, 'c'),
                new Location(BLACK_PAWN_LOW, 'd'),
                new Location(BLACK_PAWN_LOW, 'e'),
                new Location(BLACK_PAWN_LOW, 'f'),
                new Location(BLACK_PAWN_LOW, 'g'),
                new Location(BLACK_PAWN_LOW, 'h')
        };
        if (black) {
            return Arrays.asList(blackTeamInitialPawnLocations)
                    .contains(this);
        }
        return Arrays.asList(whiteTeamInitialPawnLocations)
                .contains(this);
    }

    // 2칸 혹은 한 칸이동할 수 있다.
    public boolean isInitialPawnForwardRange(Location after, int value) {
        boolean result = row + value == after.row || row + (value * 2) == after.row;

        return result && col == after.col;
    }

    public boolean isPawnForwardRange(Location after, int value) {
        boolean result = row + value == after.row;

        return result && col == after.col;
    }

    // 폰의 대각선위치인지 확인하는 메서드
    public boolean isForwardDiagonal(Location after, int value) {
        return this.row + value == after.row
                && this.col - 1 == after.col || this.col + 1 == after.col;
    }

    public Location calculateNextLocation(Location destination, int weight) {
        int rowWeight = weight;
        int colWeight = weight;

        if (row == destination.row) {
            rowWeight = 0;
        }
        if (col == destination.col) {
            colWeight = 0;
        }
        if (row > destination.row) {
            rowWeight = -1 * rowWeight;
        }
        if (col > destination.col) {
            colWeight = -1 * colWeight;
        }

        return new Location(row + rowWeight, (char) (col + colWeight));
    }

    public boolean isVertical(Location destination) {
        return col == destination.col;
    }

    boolean is(int row) {
        return this.row == row;
    }

    public int getRow() {
        return row;
    }

    public char getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return row == location.row &&
                col == location.col;
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

