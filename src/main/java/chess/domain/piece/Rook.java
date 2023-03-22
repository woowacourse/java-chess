package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {
    
    private Rook(final Color color) {
        super(color, PieceType.ROOK, List.of(Direction.N, Direction.E, Direction.S, Direction.W));
    }
    
    public static Rook create(final Color color) {
        return new Rook(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        this.checkDirection(direction);
    }
}
