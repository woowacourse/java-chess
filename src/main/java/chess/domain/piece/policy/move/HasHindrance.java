package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;

public class HasHindrance implements CanNotMoveStrategy {
    //피스 받는 게 나음
    //todo refac
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Initialized initializedPiece = (Initialized) piecesState.getPiece(from);
        return initializedPiece.hasHindrance(to, piecesState);
    }
}
