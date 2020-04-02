package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.position.Position;

public class IsNotHeadingStraightDiagonalDirection implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        return initializedPiece.isNotHeadingStraightDiagonal(to);
    }
}
