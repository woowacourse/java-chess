package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.BISHOP;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(WHITE);
    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UP_RIGHT, Movement.UP_LEFT, Movement.DOWN_RIGHT, Movement.DOWN_LEFT);

    public Bishop(final Color color) {
        super(color, BISHOP);
    }

    public static Bishop from(final Color color) {
        if (color.isBlack()) {
            return BLACK_BISHOP;
        }
        return WHITE_BISHOP;
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
        return 3;
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
