package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class HorizonDirection implements Direction {
    private static final int ROW_DISTANCE_TO_MOVE = 0;
    private static final int COLUMN_DISTANCE_TO_MOVE = 1;
    private static final int FORWARD_FACTOR = 1;
    private static final int REVERSE_FACTOR = -1;

    private HorizonDirection() {}

    private static class HorizonDirectionHolder {
        private static final HorizonDirection instance = new HorizonDirection();
    }

    public static HorizonDirection getInstance() {
        return HorizonDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        int multipleFactor = FORWARD_FACTOR;

        if (isReverseDirection) {
             multipleFactor = REVERSE_FACTOR;
        }

        Position newPosition = position.movePosition(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE * multipleFactor);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference;
    }
}
