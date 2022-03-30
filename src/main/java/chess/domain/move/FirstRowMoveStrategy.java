package chess.domain.move;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public abstract class FirstRowMoveStrategy extends MoveStrategy {

    protected boolean isMovableToTarget(Piece targetPiece, Color color) {
        if (!targetPiece.isBlank()) {
            return targetPiece.getColor() == color.oppositeColor();
        }
        return true;
    }
}
