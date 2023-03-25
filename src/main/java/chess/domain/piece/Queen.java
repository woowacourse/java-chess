package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public final class Queen extends Piece {
    private final static Queen BLACK_QUEEN = new Queen(BLACK);
    private final static Queen WHITE_QUEEN = new Queen(WHITE);
    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT,
            Movement.UP_RIGHT, Movement.UP_LEFT, Movement.DOWN_RIGHT, Movement.DOWN_LEFT);

    private Queen(final Color color) {
        super(color, QUEEN);
    }

    public static Queen from(final Color color) {
        if (color.isBlack()) {
            return BLACK_QUEEN;
        }
        return WHITE_QUEEN;
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
        return 9;
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
