package chess.model.piece;

import static chess.model.strategy.move.Direction.EAST;
import static chess.model.strategy.move.Direction.NORTH;
import static chess.model.strategy.move.Direction.NORTHEAST;
import static chess.model.strategy.move.Direction.NORTHWEST;
import static chess.model.strategy.move.Direction.SOUTH;
import static chess.model.strategy.move.Direction.SOUTHEAST;
import static chess.model.strategy.move.Direction.SOUTHWEST;
import static chess.model.strategy.move.Direction.WEST;

import chess.model.Color;
import chess.model.strategy.UnlimitedMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public final class Queen extends Piece {

    private static final List<Direction> QUEEN_DIRECTION
            = List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);

    public Queen(Color color) {
        super(color, new UnlimitedMovableStrategy(QUEEN_DIRECTION));
    }

    @Override
    public double getPointValue() {
        return Point.QUEEN.getValue();
    }
}
