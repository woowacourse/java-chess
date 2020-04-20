package chess.domain.piece.policy.move;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

public class PawnIsVerticalWithAttack implements CanNotMoveStrategy {
    //피스가 나음
    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        Piece fromPiece = piecesState.getPiece(from);
        Piece toPiece = piecesState.getPiece(to);
        return from.isVerticalDirection(to) && fromPiece.isEnemy(toPiece);
    }
}
