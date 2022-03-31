package chess.model.piece;

import chess.model.Color;
import chess.model.strategy.UnlimitedMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> ROOK_DIRECTION
            = List.of(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST);

    public Rook(Color color) {
        super(color, new UnlimitedMovableStrategy(ROOK_DIRECTION));
    }

    @Override
    public double getPointValue() {
        return Point.ROOK.getValue();
    }
}
