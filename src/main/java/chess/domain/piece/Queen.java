package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> MOVABLE_DIRECTIONS =  List.of(
            Direction.N, Direction.NE, Direction.E, Direction.SE,
            Direction.S, Direction.SW, Direction.W, Direction.NW);

    private Queen(final Color color, final PieceType pieceType, List<Direction> movableDirections) {
        super(color, pieceType, movableDirections);
    }
    
    public static Queen create(final Color color) {
        return new Queen(color, PieceType.QUEEN, MOVABLE_DIRECTIONS);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
    }
}
