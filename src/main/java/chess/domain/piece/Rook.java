package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT
    );

    public Rook(Team team) {
        super(team, Role.ROOK);
    }

    @Override
    public boolean isMovable(Square source, Square target, Direction direction) {
        return POSSIBLE_DIRECTIONS.contains(direction);
    }
}
