package chess.piece;

import static chess.path.Movement.DOWN_LEFT;
import static chess.path.Movement.DOWN_RIGHT;
import static chess.path.Movement.UP_LEFT;
import static chess.path.Movement.UP_RIGHT;

import chess.path.Movement;
import chess.path.Path;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);
        validateMovement(movement);

        return trackPath(from, to, movement);
    }

    private static void validateMovement(final Movement movement) {
        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException("Bishop이 이동할 수 없는 방향임!");
        }
    }

    private static Path trackPath(final Position from, final Position to, final Movement movement) {
        Position next = from;
        List<Position> positions = new ArrayList<>();

        while (true) {
            next = next.moveBy(movement);
            if (next.equals(to)) {
                break;
            }
            positions.add(next);
        }

        return new Path(positions);
    }

}
