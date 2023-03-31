package chess.model.domain.piece;

import chess.model.domain.position.Movement;
import chess.model.domain.position.Position;
import chess.model.exception.CantMoveFromToException;
import java.util.List;

public class King extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(Movement.U, Movement.D, Movement.R, Movement.L,
            Movement.UR, Movement.UL, Movement.DR, Movement.DL);

    public King(final Color color) {
        super(color, PieceType.KING);
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
        if (!movement.move(from).equals(to)) {
            throw new CantMoveFromToException();
        }
    }
}
