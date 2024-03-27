package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.PieceRelation;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.position.PathStatus;

public final class Rook extends Piece {

    public Rook(final PieceColor color) {
        super(color, PieceType.ROOK);
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
