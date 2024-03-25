package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.board.Movement;
import chess.domain.board.SquareStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.List;

public final class Queen extends Piece {
    private static final List<Direction> QUEEN_DIRECTION = Direction.all();

    public Queen(final PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isMovable(final Movement movement, final SquareStatus targetStatus) {
        return isMovableDirection(movement.findDirection());
    }

    private boolean isMovableDirection(final Direction direction) {
        return QUEEN_DIRECTION.contains(direction);
    }
}
