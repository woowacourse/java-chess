package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public abstract class Direction {
    protected static final int FORWARD_FACTOR = 1;
    protected static final int REVERSE_FACTOR = -1;

    private final int ROW_DISTANCE_TO_MOVE ;
    private final int COLUMN_DISTANCE_TO_MOVE;

    public Direction(int ROW_DISTANCE_TO_MOVE, int COLUMN_DISTANCE_TO_MOVE) {
        this.ROW_DISTANCE_TO_MOVE = ROW_DISTANCE_TO_MOVE;
        this.COLUMN_DISTANCE_TO_MOVE = COLUMN_DISTANCE_TO_MOVE;
    }

    Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        int multipleFactor = FORWARD_FACTOR;

        if (isReverseDirection) {
            multipleFactor = REVERSE_FACTOR;
        }

        Position newPosition = position.movePosition(ROW_DISTANCE_TO_MOVE, COLUMN_DISTANCE_TO_MOVE * multipleFactor);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    abstract int matchMoveCount(int rowDifference, int columnDifference);
}
