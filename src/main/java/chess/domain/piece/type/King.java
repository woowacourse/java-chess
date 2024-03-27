package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Movement;

public final class King extends Piece {
    public King(final PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isMovableDirection(movement.findDirection()) && isMovableDistance(movement.calculateDistance()) && isOpened;
    }

    private boolean isMovableDirection(final Direction direction) {
        return direction.isDiagonal() || direction.isCross();
    }

    private boolean isMovableDistance(final int distance) {
        return distance == 1;
    }
}
