package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final List<Integer> MOVABLE_DISTANCES = List.of(1, 2);
    private static final List<Direction> MOVABLE_DIRECTIONS = List.of(
            Direction.N, Direction.NE, Direction.E, Direction.SE,
            Direction.S, Direction.SW, Direction.W, Direction.NW);

    private King(final Color color, final PieceType pieceType, List<Direction> movableDirections) {
        super(color, pieceType, movableDirections);
    }

    public static King create(final Color color) {
        return new King(color, PieceType.KING, MOVABLE_DIRECTIONS);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
        checkDistance(start, end, MOVABLE_DISTANCES);
    }
}
