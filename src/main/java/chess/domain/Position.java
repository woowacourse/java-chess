package chess.domain;

public class Position {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 8;
    private static final int SQUARE_UNIT = 2;
    private static final int FIRST_ROW_FOR_WHITE_PAWN = 2;
    private static final int FIRST_ROW_FOR_BLACK_PAWN = 7;

    private final int col;
    private final int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
        validatePosition();
    }

    private void validatePosition() {
        if (isOutOfRange(col) || isOutOfRange(row)) {
            throw new IllegalArgumentException("체스판을 넘었습니다.");
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_LIMIT || number > MAX_LIMIT;
    }

    public boolean canMoveBackAndForth(Position position) {
        return this.col == position.col;
    }

    public boolean canMoveSideToSide(Position position) {
        return this.row == position.row;
    }

    public boolean canMoveDiagonally(Position position) {
        return Math.abs(subtractRow(position)) == Math.abs(subtractCol(position));
    }

    public int getDistanceSquare(Position position) {
        return (int) (Math.pow(subtractRow(position), SQUARE_UNIT) + Math.pow(subtractCol(position), SQUARE_UNIT));
    }

    public int subtractRow(Position position) {
        return this.row - position.row;
    }

    public int subtractCol(Position position) {
        return this.col - position.col;
    }

    public boolean isInStartingPosition() {
        return this.row == FIRST_ROW_FOR_WHITE_PAWN || this.row == FIRST_ROW_FOR_BLACK_PAWN;
    }
}
