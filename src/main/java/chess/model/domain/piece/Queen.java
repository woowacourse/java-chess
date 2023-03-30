package chess.model.domain.piece;

import static chess.model.domain.position.Movement.D;
import static chess.model.domain.position.Movement.DL;
import static chess.model.domain.position.Movement.DR;
import static chess.model.domain.position.Movement.L;
import static chess.model.domain.position.Movement.R;
import static chess.model.domain.position.Movement.U;
import static chess.model.domain.position.Movement.UL;
import static chess.model.domain.position.Movement.UR;

import chess.model.domain.position.Movement;
import java.util.List;

public class Queen extends SlidingPiece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L, UR, UL, DR, DL);

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    protected final List<Movement> getCanMoveDestination() {
        return CAN_MOVE_DESTINATION;
    }
}
