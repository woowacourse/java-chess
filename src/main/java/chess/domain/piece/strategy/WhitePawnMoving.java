package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class WhitePawnMoving implements Movable {

    private static final int INITIAL_PAWN_COORDINATE_Y = 2;

    @Override
    public boolean isPawnMoving(final Board board, final Position from, final Position to) {
        if (board.hasPiece(to)) {
            return isDiagonal(from, to);
        }
        return isForward(from, to);
    }

    private boolean isDiagonal(final Position from, final Position to) {
        Direction direction = Direction.getDirection(from, to);

        return Direction.whitePawnDiagonalStep().contains(direction);
    }

    private boolean isForward(final Position from, final Position to) {
        Direction direction = Direction.getDirection(from, to);
        if (isStarting(from)) {
            return Direction.whitePawnStartingForwardStep().contains(direction);
        }
        return Direction.whitePawnForwardStep().contains(direction);
    }

    private boolean isStarting(final Position from) {
        return from.getCoordinateY() == INITIAL_PAWN_COORDINATE_Y;
    }
}
