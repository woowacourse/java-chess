package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.Square;
import chess.domain.piece.position.Position;

public class PawnIsVerticalWithAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return from.isVerticalDirection(to) && piecesState.isOppositeTeam(from, to);
    }
}
