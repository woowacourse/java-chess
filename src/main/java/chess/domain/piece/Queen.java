package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {
    
    private Queen(final Color color) {
        super(color, PieceType.QUEEN,
                List.of(Direction.N, Direction.NE, Direction.E, Direction.SE,
                        Direction.S, Direction.SW, Direction.W, Direction.NW));
    }
    
    public static Queen create(final Color color) {
        return new Queen(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
    }
}
