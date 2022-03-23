package chess.domain;

public class Position {
    private final int col;
    private final int row;

    private Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public static Position from(String position) {
        int col = position.charAt(0) - 'a';
        int row = Character.getNumericValue(position.charAt(1)) - 1;
        return new Position(col, row);
    }

    public int getColumn() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
