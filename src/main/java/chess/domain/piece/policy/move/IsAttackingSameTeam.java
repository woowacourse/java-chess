package chess.domain.piece.policy.move;


import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class IsAttackingSameTeam implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return piecesState.isSameTeam(from, to);
    }
}
