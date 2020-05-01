package chess.domain.piece.policy.move;

import chess.domain.piece.CanNotMoveStrategy;
import chess.domain.piece.state.Pieces;
import chess.domain.position.Position;

public class HasHindranceStraightInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, Pieces pieces) {
        if (from.isNotStraightDirection(to)) {
            return false;
        }

        return pieces.hasHindranceInStraightBetween(from, to);
    }
}
