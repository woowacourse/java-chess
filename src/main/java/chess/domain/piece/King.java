package chess.domain.piece;

import static chess.domain.position.Movement.D;
import static chess.domain.position.Movement.DL;
import static chess.domain.position.Movement.DR;
import static chess.domain.position.Movement.L;
import static chess.domain.position.Movement.R;
import static chess.domain.position.Movement.U;
import static chess.domain.position.Movement.UL;
import static chess.domain.position.Movement.UR;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L, UR, UL, DR, DL);
    private static final String KING_MOVE_OVER_ONE_MESSAGE = "왕은 한 칸만 움직일 수 있습니다.";

    public King(final Color color) {
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
        validateMoveCount(from, to, movement);
        return movement;
    }

    private void validateMoveCount(final Position from, final Position to, final Movement movement) {
        if (!from.moveBy(movement).equals(to)) {
            throw new IllegalArgumentException(KING_MOVE_OVER_ONE_MESSAGE);
        }
    }
}
