package chess.domain.piece.policy.move;

import chess.domain.piece.CanNotMoveStrategy;
import chess.domain.piece.state.Pieces;
import chess.domain.position.Position;

public class HasHindranceStraightDiagonallyInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, Pieces pieces) {
        if (from.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return pieces.hasHindranceInStraightBetween(from, to);
    }
}
