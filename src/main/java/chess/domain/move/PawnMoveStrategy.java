package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;

public class PawnMoveStrategy {

    private final static List<Direction> PAWN_DIRECTIONS = List.of(Direction.NORTH,
            Direction.SOUTH,
            Direction.NORTHEAST,
            Direction.NORTHWEST,
            Direction.SOUTHEAST,
            Direction.SOUTHWEST);

    public boolean isMovable(final Board board, final Position source, final Position target) {
        return true;
    }
}
