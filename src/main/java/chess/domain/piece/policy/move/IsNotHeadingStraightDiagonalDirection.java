package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class IsNotHeadingStraightDiagonalDirection implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.isNotStraightDiagonalDirection(to);
    }
}
