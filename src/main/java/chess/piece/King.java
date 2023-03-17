package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.DOWN_LEFT;
import static chess.Movement.DOWN_RIGHT;
import static chess.Movement.LEFT;
import static chess.Movement.RIGHT;
import static chess.Movement.UP;
import static chess.Movement.UP_LEFT;
import static chess.Movement.UP_RIGHT;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.List;
import java.util.Optional;

public class King extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT,
            DOWN_RIGHT, DOWN_LEFT);

    public King(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);

        if (!from.moveBy(movement).equals(to)) {
            throw new IllegalStateException();
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

        return new Path();
    }
}
