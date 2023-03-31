package chess.model.domain.piece;

import static chess.model.domain.position.Movement.D;
import static chess.model.domain.position.Movement.L;
import static chess.model.domain.position.Movement.R;
import static chess.model.domain.position.Movement.U;

import chess.model.domain.position.Movement;
import java.util.List;

public class Rook extends SlidingPiece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L);

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    protected final List<Movement> getCanMoveDestination() {
        return CAN_MOVE_DESTINATION;
    }
}
