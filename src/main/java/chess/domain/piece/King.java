package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {
    
    private static final List<Integer> MOVABLE_DISTANCES = List.of(1, 2);
    
    private King(final Color color) {
        super(color, PieceType.KING,
                List.of(Direction.N, Direction.NE, Direction.E, Direction.SE,
                        Direction.S, Direction.SW, Direction.W, Direction.NW));
    }
    
    public static King create(final Color color) {
        return new King(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        this.checkDirection(direction);
        this.checkDistance(start, end, MOVABLE_DISTANCES);
    }
}
