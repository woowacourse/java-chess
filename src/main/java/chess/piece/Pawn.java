package chess.piece;

import static chess.path.Movement.DOWN;
import static chess.path.Movement.DOWN_LEFT;
import static chess.path.Movement.DOWN_RIGHT;
import static chess.path.Movement.UP;
import static chess.path.Movement.UP_LEFT;
import static chess.path.Movement.UP_RIGHT;

import chess.path.Movement;
import chess.path.Path;
import chess.position.Position;
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
    private static final Map<Color, Integer> INITIAL_POSITION_RANK = Map.of(
            Color.BLACK, 7,
            Color.WHITE, 2
    );
    private static final Map<Color, Integer> INITIAL_RANK_DIFFERENCE = Map.of(
            Color.BLACK, -2,
            Color.WHITE, 2
    );


    public Pawn(final Color color) {
        super(color);
    }

    public Path searchPathTo(Position from, Position to, Optional<Piece> destination) {
        Movement movement = to.convertMovement(from);

        if (destination.isEmpty()) {
            return searchPathToEmptyPosition(from, to, movement);
        }

        return searchPathToEnemyPosition(destination.get(), movement);
    }

    private Path searchPathToEnemyPosition(final Piece destination, final Movement movement) {
        if (!destination.color.isDifferentColor(color)) {
            throw new IllegalStateException("같은 색의 말이 있는 곳으로는 이동할 수 없음!");
        }

        if (!CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement)) {
            throw new IllegalStateException("움직일 수 있는 방향이 아님!");
        }

        return new Path();
    }

    private Path searchPathToEmptyPosition(final Position from, final Position to, final Movement movement) {
        if (movement != CAN_MOVE_EMPTY_DESTINATION.get(color)) {
            throw new IllegalStateException("움직일 수 있는 방향이 아님!");
        }

        if (isMoveOneStep(from, to)) {
            return new Path();
        }

        if (isInInitialPosition(from) && isMoveTwoSteps(from, to)) {
            final Position wayPoint = from.moveBy(CAN_MOVE_EMPTY_DESTINATION.get(color));

            return new Path(wayPoint);
        }

        throw new IllegalStateException("움직일 수 있는 방법이 아님!");
    }

    private boolean isMoveOneStep(final Position from, final Position to) {
        return Math.abs(rankDifference(from, to)) == 1;
    }

    private boolean isMoveTwoSteps(final Position from, final Position to) {
        return rankDifference(from, to) == INITIAL_RANK_DIFFERENCE.get(color);
    }

    private boolean isInInitialPosition(final Position from) {
        return from.isSameRank(INITIAL_POSITION_RANK.get(color));
    }

    private int rankDifference(final Position from, final Position to) {
        return to.rankDifference(from);
    }
}
