package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.HashMap;
import java.util.Map;

public class Bishop extends Piece {
    private static final Score SCORE = new Score(3);

    private Bishop(String name, boolean color) {
        super(name, SCORE, color);
    }

    public static Bishop of(String name, boolean color) {
        return new Bishop(name, color);
    }

    public static Map<Position, Bishop> initialBishopPieces() {
        return new HashMap<Position, Bishop>() {{
            put(Position.of("c8"), Bishop.of("B", true));
            put(Position.of("f8"), Bishop.of("B", true));
            put(Position.of("c1"), Bishop.of("b", false));
            put(Position.of("f5"), Bishop.of("b", false));
        }};
    }

    public boolean checkDiagonal(Position start, Position end) {
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
        return (rowDiff != 0 && colDiff != 0) && rowDiff == colDiff;
    }

    private boolean isEmptyPosition(Map<Position, Piece> board, Position nextPosition) {
        return !board.containsKey(nextPosition);
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        if (!checkDiagonal(start, end)) {
            return false;
        }

        Direction direction = Direction.findDiagonalDirection(start, end);
        do {
            start = start.move(direction);
        } while (!start.equals(end) && isEmptyPosition(board, start));

        return start.equals(end) && (isEmptyPosition(board, end) || !this.isSameColor(board.get(end)));
    }
}
