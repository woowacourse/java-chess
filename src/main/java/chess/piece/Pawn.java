package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.DOWN_LEFT;
import static chess.Movement.DOWN_RIGHT;
import static chess.Movement.UP;
import static chess.Movement.UP_LEFT;
import static chess.Movement.UP_RIGHT;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Pawn extends Piece {

    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, DOWN,
            Color.WHITE, UP
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(DOWN_RIGHT, DOWN_LEFT),
            Color.WHITE, List.of(UP_RIGHT, UP_LEFT)
    );

    public Pawn(final Color color) {
        super(color);
    }

    public Path searchPathTo(Position from, Position to, Optional<Piece> destination) {
        Movement movement = to.convertMovement(from);

        if (destination.isEmpty() && movement == CAN_MOVE_EMPTY_DESTINATION.get(color)) {

            if (color.isWhite() && from.rank().value() == 2
                    && rankDifference(from, to) == 2) {
                final Position wayPoint = from.moveBy(UP);

                return new Path(wayPoint);
            }

            if (color.isBlack() && from.rank().value() == 7
                    && rankDifference(from, to) == -2) {
                Position wayPoint = from.moveBy(DOWN);

                return new Path(wayPoint);
            }

            return new Path();
        }
        // 상대 말인 경우
        if (destination.isPresent()
                && destination.get().color.isDifferentColor(color)
                && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement)) {
            return new Path();
        }
        throw new IllegalStateException();
    }

    private int rankDifference(final Position from, final Position to) {
        return to.rankDifference(from);
    }
}
