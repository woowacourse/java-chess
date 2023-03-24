package chess.domain.piece;

import chess.domain.position.Movement;
import java.util.List;

public class Bishop extends SlidingPiece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UR, Movement.UL, Movement.DR, Movement.DL);

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected final List<Movement> getCanMoveDestination() {
        return CAN_MOVE_DESTINATION;
    }
}
