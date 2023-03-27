package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;

public final class Knight extends Piece {
    private static final Knight BLACK_KNIGHT = new Knight(BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(WHITE);
    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    Movement.UP_UP_RIGHT, Movement.UP_UP_LEFT, Movement.RIGHT_RIGHT_UP, Movement.RIGHT_RIGHT_DOWN,
                    Movement.DOWN_DOWN_RIGHT, Movement.DOWN_DOWN_LEFT, Movement.LEFT_LEFT_UP, Movement.LEFT_LEFT_DOWN
            );
    private static final int POSITION_DIFFERENCE = 3;

    private Knight(final Color color) {
        super(color, KNIGHT);
    }

    public static Knight from(final Color color) {
        if (color.isBlack()) {
            return BLACK_KNIGHT;
        }
        return WHITE_KNIGHT;
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        if (destination != null) {
            validateSameColor(destination);
        }

        Movement movement = to.convertMovement(from);
        validateMovement(movement, CAN_MOVE_DESTINATION);
        validatePositionDifference(from, to);

        return new Path();
    }

    private void validatePositionDifference(final Position from, final Position to) {
        int rankDifference = Math.abs(to.rankGap(from));
        int fileDifference = Math.abs(to.fileGap(from));

        boolean hasInvalidPositionDifference = rankDifference + fileDifference != POSITION_DIFFERENCE;
        if (hasInvalidPositionDifference) {
            throw new IllegalStateException("Knight가 이동할 수 없는 움직임임!");
        }
    }
}
