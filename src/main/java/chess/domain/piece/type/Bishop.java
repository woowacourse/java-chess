package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Movement;

public final class Bishop extends Piece {

    public Bishop(final PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isMovableDirection(movement.findDirection()) && isOpened;
    }

    private boolean isMovableDirection(final Direction direction) {
        return direction.isDiagonal();
    }
}
