package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    public static final List<Direction> MOVABLE_DIRECTIONS = List.of(
            Direction.N, Direction.E, Direction.S, Direction.W);

    private Rook(final Color color, final PieceType pieceType, List<Direction> movableDirections) {
        super(color, pieceType, movableDirections);
    }
    
    public static Rook create(final Color color) {
        return new Rook(color, PieceType.ROOK, MOVABLE_DIRECTIONS);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
    }
}
