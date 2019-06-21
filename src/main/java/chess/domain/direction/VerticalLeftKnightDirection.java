package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class VerticalLeftKnightDirection implements Direction {
    private VerticalLeftKnightDirection () {}

    private static class VerticalLeftKnightDirectionHolder {
        private static final VerticalLeftKnightDirection instance = new VerticalLeftKnightDirection();
    }

    public static VerticalLeftKnightDirection getInstance () {
        return VerticalLeftKnightDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if (isReverseDirection) {
            Position newPosition = position.movePosition(-2,1);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(2,-1);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference * (-1);
    }
}
