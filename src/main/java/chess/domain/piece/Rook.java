package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> ROOK_DIRECTION = List.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return ROOK_DIRECTION.contains(movement.direction());
    }
}
