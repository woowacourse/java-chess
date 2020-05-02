package chess.domain.policy.move;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.Position;

class IsNotHeadingStraightDiagonalDirection implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.isNotStraightDiagonalDirection(to);
    }
}
