package chess.domain.piece.policy.move;


import chess.domain.piece.CanNotMoveStrategy;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.Position;

public class IsAttackingSameTeam implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return piecesState.isSameTeam(from, to);
    }
}
