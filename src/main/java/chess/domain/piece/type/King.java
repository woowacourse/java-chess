package chess.domain.piece.type;

import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.piece.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

public final class King extends Piece {
    public King(final PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation) {
        return isMovableDirection(movement.findDirection()) && isMovableDistance(movement.calculateDistance());
    }

    private boolean isMovableDirection(final Direction direction) {
        return type.contains(direction);
    }

    private boolean isMovableDistance(final int distance) {
        return distance == 1;
    }
}
