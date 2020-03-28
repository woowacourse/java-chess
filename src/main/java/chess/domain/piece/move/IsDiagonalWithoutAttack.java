package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.state.Initialized;
import chess.domain.position.Position;

public class IsDiagonalWithoutAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Initialized initializedPiece, Position to, Board board) {
        InitializedPawn initializedPawn = (InitializedPawn) initializedPiece;
        Piece piece = board.getPiece(to);
        return initializedPawn.isHeadingDiagonal(to) && (initializedPawn.isSameTeam(piece) || piece.isBlank()) ;
    }
}
