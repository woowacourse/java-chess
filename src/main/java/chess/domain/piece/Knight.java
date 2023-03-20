package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {
    
    public static final List<Integer> MOVABLE_DISTANCE = List.of(5);
    private static final List<Direction> MOVABLE_DIRECTIONS = List.of(
            Direction.ENE, Direction.ESE, Direction.SSW, Direction.SSE,
            Direction.WNW, Direction.WSW, Direction.NNW, Direction.NNE);
    
    private Knight(final Color color, final PieceType pieceType, List<Direction> movableDirections) {
        super(color, pieceType, movableDirections);
    }
    
    public static Knight create(final Color color) {
        return new Knight(color, PieceType.KNIGHT, MOVABLE_DIRECTIONS);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
        checkDistance(start, end, MOVABLE_DISTANCE);
    }
}
