package chess.model.domain.piece;

import chess.model.domain.position.Movement;
import chess.model.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    Movement.UUR, Movement.UUL, Movement.RRU, Movement.RRD,
                    Movement.DDR, Movement.DDL, Movement.LLU, Movement.LLD
            );

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        final Movement movement = to.convertMovement(from);
        validateMovable(movement, CAN_MOVE_DESTINATION);
        validateManhattanDistance(from, to);
        return movement;
    }

    private void validateManhattanDistance(final Position from, final Position to) {
        if (from.rankDifference(to) + from.fileDifference(to) != 3) {
            throw new IllegalArgumentException();
        }
    }
}
