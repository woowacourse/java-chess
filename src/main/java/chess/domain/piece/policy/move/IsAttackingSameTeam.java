package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.position.Position;

public class IsAttackingSameTeam implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Piece exPiece = board.getPiece(to);
        return initializedPiece.isSameTeam(exPiece);
    }
}
