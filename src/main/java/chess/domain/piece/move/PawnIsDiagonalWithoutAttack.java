package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class PawnIsDiagonalWithoutAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        Pawn initializedPawn = (Pawn) initializedPiece;
        Piece piece = board.getPiece(to);
        return initializedPawn.isHeadingDiagonal(to) && (initializedPawn.isSameTeam(piece) || piece.isBlank()) ;
    }
}
