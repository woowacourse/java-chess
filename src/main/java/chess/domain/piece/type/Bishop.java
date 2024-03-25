package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.board.Movement;
import chess.domain.board.SquareStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.List;

public final class Bishop extends Piece {
    private static final List<Direction> BISHOP_DIRECTION = Direction.diagonal();

    public Bishop(final PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isMovable(final Movement movement, final SquareStatus targetStatus) {
        return isMovableDirection(movement.findDirection());
    }

    private boolean isMovableDirection(final Direction direction) {
        return BISHOP_DIRECTION.contains(direction);
    }
}
