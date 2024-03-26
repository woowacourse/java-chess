package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.board.Movement;
import chess.domain.board.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

public final class Rook extends Piece {

    public Rook(final PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation) {
        return isMovableDirection(movement.findDirection());
    }

    private boolean isMovableDirection(final Direction direction) {
        return type.contains(direction);
    }
}
