package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.RIGHT;
import static chess.Movement.UP;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rook extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(UP, DOWN, RIGHT, LEFT);

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);
        validateMovement(movement);

        return trackPath(from, to, movement);
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

    private static void validateMovement(final Movement movement) {
        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }
    }
}
