package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.board.Movement;
import chess.domain.board.SquareStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.List;

public final class King extends Piece {
    private static final List<Direction> KING_DIRECTION = Direction.all();

    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isMovable(final Movement movement, final SquareStatus targetStatus) {
        return isMovableDirection(movement.findDirection()) && isMovableDistance(movement.calculateDistance());
    }

    private boolean isMovableDirection(Direction direction) {
        return KING_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(int distance) {
        return distance == 1;
    }
}
