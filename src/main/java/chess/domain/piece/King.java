package chess.domain.piece;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT,
            Movement.UP_RIGHT, Movement.UP_LEFT, Movement.DOWN_RIGHT, Movement.DOWN_LEFT);

    public King(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        if (destination != null) {
            validateSameColor(destination);
        }

        Movement movement = to.convertMovement(from);
        validateMovement(movement, CAN_MOVE_DESTINATION);
        validateAvailableDestination(from, to, movement);

        return new Path();
    }

    private void validateAvailableDestination(final Position from, final Position to, final Movement movement) {
        if (!from.moveBy(movement).equals(to)) {
            throw new IllegalStateException("갈 수 없는 도착지입니다.");
        }
    }
}
