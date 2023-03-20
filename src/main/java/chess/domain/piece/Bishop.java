package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.List;

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
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        final Movement movement = to.convertMovement(from);
        validateMovable(movement, CAN_MOVE_DESTINATION);
        return movement;
    }
}
