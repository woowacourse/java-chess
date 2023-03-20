package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {

    public static final List<Direction> MOVABLE_DIRECTIONS = List.of(
            Direction.NE, Direction.SE, Direction.SW, Direction.NW);

    private Bishop(final Color color, final PieceType pieceType, List<Direction> movableDirections) {
        super(color, pieceType, movableDirections);
    }

    public static Bishop create(final Color color) {
        return new Bishop(color, PieceType.BISHOP, MOVABLE_DIRECTIONS);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
    }
}
