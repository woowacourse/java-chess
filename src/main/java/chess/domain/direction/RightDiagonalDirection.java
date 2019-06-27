package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class RightDiagonalDirection implements Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;
    private static final int REVERSE_FACTOR = -1;

    private RightDiagonalDirection() {}

    private static class RithtDiagonalDirectionHolder {
        private static final RightDiagonalDirection instance = new RightDiagonalDirection();
    }

    public static RightDiagonalDirection getInstance() {
        return RithtDiagonalDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if (isReverseDirection) {
            Position newPosition = position.movePosition(ROW_DISTANCE_TO_MOVE * REVERSE_FACTOR, COLUMN_DISTANCE_TO_MOVE * REVERSE_FACTOR);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
