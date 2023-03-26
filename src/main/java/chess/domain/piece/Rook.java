package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT
    );

    public Rook(Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public void validateMovableRange(Square source, Square target) {
        Direction direction = Direction.calculateDirection(source, target);

        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new PieceCanNotMoveException();
        }
    }
}
