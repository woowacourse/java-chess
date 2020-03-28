package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class HasHindrance implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        return initializedPiece.hasHindrance(to, board);
    }
}
