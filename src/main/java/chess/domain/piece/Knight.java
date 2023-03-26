package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.KNIGHT_UP_RIGHT, Direction.KNIGHT_RIGHT_UP, Direction.KNIGHT_RIGHT_DOWN,
            Direction.KNIGHT_DOWN_RIGHT, Direction.KNIGHT_DOWN_LEFT, Direction.KNIGHT_LEFT_DOWN,
            Direction.KNIGHT_LEFT_UP, Direction.KNIGHT_UP_LEFT
    );

    public Knight(Team team) {
        super(team, Role.KNIGHT);
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new PieceCanNotMoveException();
        }
    }
}
