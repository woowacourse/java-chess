package chess.piece.movestrategy;

import chess.coordinate.Vector;
import chess.piece.Piece;

public class DiagonalPawnMoveStrategy implements PawnMoveStrategy {
    @Override
    public boolean support(Vector vector) {
        return vector.isDiagonal();
    }

    @Override
    public boolean movable(Vector vector, Piece targetPiece) {
        return !targetPiece.isBlank();
    }
}
