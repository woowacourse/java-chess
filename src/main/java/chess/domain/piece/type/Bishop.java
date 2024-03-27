package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceRelation;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.position.PathStatus;

public final class Bishop extends Piece {

    public Bishop(final PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final PathStatus pathStatus) {
        return isMovableDirection(movement.findDirection()) && isNotBlocked(pathStatus);
    }

    private boolean isMovableDirection(final Direction direction) {
        return type.contains(direction);
    }

    private boolean isNotBlocked(final PathStatus pathStatus) {
        return pathStatus.isOpen();
    }
}
