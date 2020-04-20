package chess.domain.piece.policy.move;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;

public class HasHindrance implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Piece piece = piecesState.getPiece(from);
        return piece.hasHindrance(to, piecesState);
    }
}
