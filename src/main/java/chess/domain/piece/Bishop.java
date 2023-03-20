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
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);
        final Movement movement = to.convertMovement(from);
        validateMovable(movement, CAN_MOVE_DESTINATION);
        return generatePathFromTo(from, to, movement);
    }
}
