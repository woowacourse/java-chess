package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.RIGHT_UP, Direction.RIGHT_DOWN, Direction.LEFT_UP, Direction.LEFT_DOWN
    );

    public King(final Team team) {
        super(team, Role.KING);
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Direction direction) {
        if (POSSIBLE_DIRECTIONS.contains(direction)) {
            return source.isMovableToTarget(target, direction.getFile(), direction.getRank());
        }
        return false;
    }
}
