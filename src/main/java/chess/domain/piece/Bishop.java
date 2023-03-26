package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.RIGHT_UP, Direction.RIGHT_DOWN, Direction.LEFT_UP, Direction.LEFT_DOWN
    );

    public Bishop(Team team) {
        super(team, PieceType.BISHOP);
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new PieceCanNotMoveException();
        }
    }
}
