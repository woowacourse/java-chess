package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.DOWN_LEFT;
import static chess.domain.Direction.DOWN_RIGHT;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static chess.domain.Direction.UP_LEFT;
import static chess.domain.Direction.UP_RIGHT;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class King extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;
    private static final List<Direction> KING_DIRECTION;

    static {
        KING_DIRECTION = List.of(
                UP, DOWN, LEFT, RIGHT,
                UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        );
    }

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return KING_DIRECTION.contains(movement.direction()) && movement.calculateMaxDistance() == MAX_MOVE_DISTANCE;
    }
}
