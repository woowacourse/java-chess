package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {
    
    public static final List<Integer> MOVABLE_DISTANCE = List.of(5);
    
    private Knight(final Color color) {
        super(color, PieceType.KNIGHT,
                List.of(Direction.ENE, Direction.ESE, Direction.SSW, Direction.SSE,
                        Direction.WNW, Direction.WSW, Direction.NNW, Direction.NNE));
    }
    
    public static Knight create(final Color color) {
        return new Knight(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        this.checkDirection(direction);
        this.checkDistance(start, end, MOVABLE_DISTANCE);
    }
}
