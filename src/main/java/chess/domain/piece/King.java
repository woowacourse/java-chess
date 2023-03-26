package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT,
            Direction.RIGHT_UP, Direction.RIGHT_DOWN, Direction.LEFT_UP, Direction.LEFT_DOWN
    );

    public King(Team team) {
        super(team, Role.KING);
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new PieceCanNotMoveException();
        }
        validateOneStep(source, target, direction);
    }

    private void validateOneStep(Square source, Square target, Direction direction) {
        if (!source.isMovableToTarget(target, direction.getFile(), direction.getRank())) {
            throw new PieceCanNotMoveException();
        }
    }
}
