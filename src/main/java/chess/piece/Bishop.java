package chess.piece;

import static chess.Movement.DL;
import static chess.Movement.DR;
import static chess.Movement.UL;
import static chess.Movement.UR;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(UR, UL, DR, DL);

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

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
