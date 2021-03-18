package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public class KnightMoveStrategy extends JumpMoveStrategy {

    private static final int FORWARD_JUMP_RANGE = 2;
    private static final int SIDE_JUMP_RANGE = 1;

    @Override
    protected void checkValidTargetPosition(Position source, Position target) {
        int xDistanceAbs = Math.abs(source.computeHorizontalDistance(target));
        int yDistanceAbs = Math.abs(source.computeVerticalDistance(target));

        if (!isKnightMoveType(xDistanceAbs, yDistanceAbs)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private boolean isKnightMoveType(int xDistance, int yDistance) {
        return (xDistance == FORWARD_JUMP_RANGE) && (yDistance == SIDE_JUMP_RANGE) ||
            (xDistance == SIDE_JUMP_RANGE) && (yDistance == FORWARD_JUMP_RANGE);
    }
}
