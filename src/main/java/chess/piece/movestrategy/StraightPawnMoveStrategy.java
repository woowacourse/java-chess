package chess.piece.movestrategy;

import chess.coordinate.Vector;
import chess.piece.Piece;

public class StraightPawnMoveStrategy implements PawnMoveStrategy {
    @Override
    public boolean support(Vector vector) {
        return vector.isStraight();
    }

    @Override
    public boolean movable(Vector vector, Piece targetPiece) {
        return targetPiece.isBlank();
    }
}
