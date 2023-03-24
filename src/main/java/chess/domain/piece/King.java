package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.KING;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;

public final class King extends Piece {

    private static final King WHITE_KING = new King(WHITE);
    private static final King BLACK_KING = new King(BLACK);

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(
            Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT,
            Movement.UP_RIGHT, Movement.UP_LEFT, Movement.DOWN_RIGHT, Movement.DOWN_LEFT);

    public King(final Color color) {
        super(color, KING);
    }

    public static King from(Color color) {
        if (color.isBlack()) {
            return BLACK_KING;
        }
        return WHITE_KING;
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {

        if (destination != null) {
            validateSameColor(destination);
        }

        Movement movement = to.convertMovement(from);
        validateMovement(movement, CAN_MOVE_DESTINATION);
        validateAvailableDestination(from, to, movement);

        return new Path();
    }

    @Override
    public double calculateScore(final boolean ignored) {
        return 0;
    }

    private void validateAvailableDestination(final Position from, final Position to, final Movement movement) {
        if (!from.moveBy(movement).equals(to)) {
            throw new IllegalStateException("갈 수 없는 도착지입니다.");
        }
    }
}
