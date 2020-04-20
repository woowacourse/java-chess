package chess.domain.piece.policy.move;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class IsNotHeadingForward implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Piece piece = piecesState.getPiece(from);
        Direction forwardDirection = piece.getForwardDirection();
        return to.isNotForward(from, forwardDirection);
    }
}
