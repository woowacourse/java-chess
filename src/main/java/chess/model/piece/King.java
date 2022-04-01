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
import chess.model.strategy.LimitedMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public final class King extends Piece {

    private static final int KING_MOVE_LIMIT = 1;
    private static final List<Direction> KING_DIRECTION
            = List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);

    public King(Color color) {
        super(color, new LimitedMovableStrategy(KING_DIRECTION, KING_MOVE_LIMIT));
    }

    @Override
    public double getPointValue() {
        return Point.KING.getValue();
    }
}
