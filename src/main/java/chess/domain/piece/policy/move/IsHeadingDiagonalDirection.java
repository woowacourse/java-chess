package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;

public class IsHeadingDiagonalDirection implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        return initializedPiece.isHeadingHeadingDiagonal(to);
    }
}
