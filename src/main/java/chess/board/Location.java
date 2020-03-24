package chess.board;

public class Location {
    private final int row;
    private final char col;

    public Location(int row, char col) {
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
}
