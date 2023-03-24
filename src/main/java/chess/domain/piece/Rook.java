package chess.domain.piece;

import static chess.domain.path.Movement.DOWN;
import static chess.domain.path.Movement.LEFT;
import static chess.domain.path.Movement.RIGHT;
import static chess.domain.path.Movement.UP;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.ROOK;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public final class Rook extends Piece {
    private static final Rook BLACK_ROOK = new Rook(BLACK);
    private static final Rook WHITE_ROOK = new Rook(WHITE);
    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(UP, DOWN, RIGHT, LEFT);

    public Rook(final Color color) {
        super(color, ROOK);
    }

    public static Rook from(Color color) {
        if (color.isBlack()) {
            return BLACK_ROOK;
        }
        return WHITE_ROOK;
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

    @Override
    public double calculateScore(final boolean ignored) {
        return 5;
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
