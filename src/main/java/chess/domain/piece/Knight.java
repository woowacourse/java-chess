package chess.domain.piece;

import static chess.domain.position.Movement.DDL;
import static chess.domain.position.Movement.DDR;
import static chess.domain.position.Movement.LLD;
import static chess.domain.position.Movement.LLU;
import static chess.domain.position.Movement.RRD;
import static chess.domain.position.Movement.RRU;
import static chess.domain.position.Movement.UUL;
import static chess.domain.position.Movement.UUR;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    UUR, UUL, RRU, RRD,
                    DDR, DDL, LLU, LLD
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
