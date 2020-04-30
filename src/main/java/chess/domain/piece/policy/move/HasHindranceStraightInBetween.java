package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class HasHindranceStraightInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        if (from.isNotStraightDirection(to)) {
            return false;
        }

        return piecesState.hasHindranceInStraightBetween(from, to);
    }
}
