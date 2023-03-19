package chess.piece;

import static chess.path.Movement.DOWN_DOWN_LEFT;
import static chess.path.Movement.DOWN_DOWN_RIGHT;
import static chess.path.Movement.LEFT_LEFT_DOWN;
import static chess.path.Movement.LEFT_LEFT_UP;
import static chess.path.Movement.RIGHT_RIGHT_DOWN;
import static chess.path.Movement.RIGHT_RIGHT_UP;
import static chess.path.Movement.UP_UP_LEFT;
import static chess.path.Movement.UP_UP_RIGHT;

import chess.path.Movement;
import chess.path.Path;
import chess.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    UP_UP_RIGHT, UP_UP_LEFT, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
                    DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, LEFT_LEFT_UP, LEFT_LEFT_DOWN
            );
    private static final int POSITION_DIFFERENCE = 3;

    public Knight(final Color color) {
        super(color);
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
        int rankDifference = Math.abs(to.rankDifference(from));
        int fileDifference = Math.abs(to.fileDifference(from));

        boolean hasInvalidPositionDifference = rankDifference + fileDifference != POSITION_DIFFERENCE;
        if (hasInvalidPositionDifference) {
            throw new IllegalStateException("Knight가 이동할 수 없는 움직임임!");
        }
    }
}
