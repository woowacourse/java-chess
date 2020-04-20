package chess.domain.piece.policy.move;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class PawnIsDiagonalWithoutAttack implements CanNotMoveStrategy {
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Piece fromPiece = piecesState.getPiece(from);
        Piece toPiece = piecesState.getPiece(to);
        return from.isDiagonalDirection(to) && (fromPiece.isSameTeam(toPiece) || toPiece.isBlank());
    }
}
