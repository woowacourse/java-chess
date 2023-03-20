package chess.domain.piece;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, Movement.DOWN,
            Color.WHITE, Movement.UP
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(Movement.DOWN_RIGHT, Movement.DOWN_LEFT),
            Color.WHITE, List.of(Movement.UP_RIGHT, Movement.UP_LEFT)
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

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        Movement movement = to.convertMovement(from);

        if (destination == null) {
            return searchPathToEmptyPosition(from, to, movement);
        }

        return searchPathToEnemyPosition(destination, movement);
    }

    private Path searchPathToEnemyPosition(final Piece destination, final Movement movement) {
        if (!destination.color.isDifferentColor(color)) {
            throw new IllegalStateException("같은 색의 말이 있는 곳으로는 이동할 수 없음!");
        }

        validateMovement(movement, CAN_MOVE_ENEMY_DESTINATION.get(color));

        return new Path();
    }

    private Path searchPathToEmptyPosition(final Position from, final Position to, final Movement movement) {
        validateMovement(movement, List.of(CAN_MOVE_EMPTY_DESTINATION.get(color)));

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
        return Math.abs(to.rankGap(from)) == 1;
    }

    private boolean isMoveTwoSteps(final Position from, final Position to) {
        return to.rankGap(from) == INITIAL_RANK_DIFFERENCE.get(color);
    }

    private boolean isInInitialPosition(final Position from) {
        return from.isSameRank(INITIAL_POSITION_RANK.get(color));
    }
}
