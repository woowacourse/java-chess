package chess.domain.piece.policy.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.piece.Pawn;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.position.Position;

public class PawnIsDiagonalWithoutAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Pawn initializedPawn = (Pawn) initializedPiece;
        Piece piece = board.getPiece(to);
        return initializedPawn.isHeadingHeadingDiagonal(to) && (initializedPawn.isSameTeam(piece) || piece.isBlank()) ;
    }
}
