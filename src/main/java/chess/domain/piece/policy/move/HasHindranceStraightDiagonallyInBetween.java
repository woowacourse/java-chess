package chess.domain.piece.policy.move;

import chess.domain.piece.CanNotMoveStrategy;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.Position;

public class HasHindranceStraightDiagonallyInBetween implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        if (from.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return piecesState.hasHindranceInStraightBetween(from, to);
    }
}
