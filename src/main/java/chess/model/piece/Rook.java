package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.UnlimitedMovableStrategy;
import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> ROOK_DIRECTION
            = List.of(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST);

    public Rook(Color color, Square square) {
        super(color, square, new UnlimitedMovableStrategy(ROOK_DIRECTION));
    }

    @Override
    public Point getPoint() {
        return Point.ROOK;
    }
}
