package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class IsAttackingSameTeam implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Piece exPiece = board.getPiece(to);
        return initializedPiece.isSameTeam(exPiece);
    }
}
