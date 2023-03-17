package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    UP_UP_RIGHT, UP_UP_LEFT, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN,
                    DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, LEFT_LEFT_UP, LEFT_LEFT_DOWN
            );

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);

        int rankDifference = Math.abs(to.rankDifference(from));
        int fileDifference = Math.abs(to.fileDifference(from));

        if (rankDifference + fileDifference != 3) {
            throw new IllegalStateException();
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

        return new Path();
    }
}
