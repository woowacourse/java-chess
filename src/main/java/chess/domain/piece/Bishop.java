package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UR, Movement.UL, Movement.DR, Movement.DL);

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

        return generatePathFromTo(from, to, movement);
    }
}
