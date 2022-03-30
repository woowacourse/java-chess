package chess.domain.move;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public abstract class FirstRowMoveStrategy implements MoveStrategy {

    protected boolean isMovableToTarget(Piece targetPiece, Color sourceColor) {
        if (!targetPiece.isBlank()) {
            return targetPiece.getColor() == sourceColor.oppositeColor();
        }
        return true;
    }
}
