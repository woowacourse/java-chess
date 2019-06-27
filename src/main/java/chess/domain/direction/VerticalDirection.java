package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class VerticalDirection implements Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 1;
    private static final int COLUMN_DISTANCE_TO_MOVE = 0;
    private static final int REVERSE_FACTOR = -1;

    private VerticalDirection() {}

    private static class VerticalDirectionHolder {
        private static final VerticalDirection instance = new VerticalDirection();
    }

    public static VerticalDirection getInstance() {
        return VerticalDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if (isReverseDirection) {
            Position newPosition = position.movePosition(ROW_DISTANCE_TO_MOVE * REVERSE_FACTOR, COLUMN_DISTANCE_TO_MOVE);
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
