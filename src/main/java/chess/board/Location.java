package chess.board;

import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.toDegrees;

public class Location {
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
        boolean rowFlag = abs(row - destination.row) <= 1;
        boolean colFlag = abs(col - destination.col) <= 1;

        return rowFlag && colFlag;
    }

    public boolean isKnightRange(Location destination) {
        int[] dx = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] dy = {1, -1, -2, 2, -2, 2, -1, 1};

        for(int i = 0; i < dx.length; i++) {
            int nx = this.row + dx[i];
            int ny = this.col + dy[i];
            if(destination.row == nx && destination.col == ny) {
                return true;
            }
        }
        return false;
    }

    public boolean isQueenRang(Location destination) {
        return isDiagonal(destination) || isStraight(destination);
    }

    public boolean isStraight(Location destination) {
        return this.row == destination.row || this.col == destination.col;
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
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
