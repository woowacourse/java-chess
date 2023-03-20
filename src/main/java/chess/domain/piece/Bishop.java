package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {
    
    private Bishop(final Color color) {
        super(color, PieceType.BISHOP, List.of(Direction.NE, Direction.SE, Direction.SW, Direction.NW));
    }
    
    public static Bishop create(final Color color) {
        return new Bishop(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
    }
}
