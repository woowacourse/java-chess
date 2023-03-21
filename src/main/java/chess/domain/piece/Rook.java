package chess.domain.piece;

import static chess.domain.piece.PieceType.ROOK;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(Movement.UP, Movement.DOWN, Movement.RIGHT,
            Movement.LEFT);

    public Rook(final Color color) {
        super(color, ROOK);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        if (destination != null) {
            validateSameColor(destination);
        }

        Movement movement = to.convertMovement(from);
        validateMovement(movement, CAN_MOVE_DESTINATION);

        return trackPath(from, to, movement);
    }

    private Path trackPath(final Position from, final Position to, final Movement movement) {
        Position next = from;
        List<Position> positions = new ArrayList<>();
        while (true) {
            next = next.moveBy(movement);
            if (next.equals(to)) {
                break;
            }
            positions.add(next);
        }
        return new Path(positions);
    }
}
