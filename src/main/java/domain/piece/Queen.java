package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece {
    private static final Score SCORE = Score.of(9);

    private Queen(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Queen of(String name, boolean color) {
        return new Queen(name, color);
    }

    public static Map<Position, Queen> initialQueenPieces() {
        return new HashMap<Position, Queen>() {{
            put(Position.of("d8"), Queen.of("Q", true));
            put(Position.of("d3"), Queen.of("q", false));
        }};
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        boolean result = canMoveDiagonal(board, start, end);
        if (!result) {
            result = canMoveStraight(board, start, end);
        }
        return result;
    }

    private boolean canMoveStraight(Map<Position, Piece> board, Position start, Position end) {
        if (!isLinear(start, end)) {
            return false;
        }

        Direction direction = getLinearDirection(start, end);
        do {
            start = start.move(direction);
        } while (!start.equals(end) && isEmptyPosition(board, start));

        return start.equals(end) && (isEmptyPosition(board, end) || !this.isSameColor(board.get(end)));
    }


    private boolean canMoveDiagonal(Map<Position, Piece> board, Position start, Position end) {
        if (!isDiagonal(start, end)) {
            return false;
        }

        Direction direction = getDiagonalDirection(start, end);
        do {
            start = start.move(direction);
        } while (!start.equals(end) && isEmptyPosition(board, start));

        return start.equals(end) && (isEmptyPosition(board, end) || !this.isSameColor(board.get(end)));
    }
}
