package chess.model.piece;

import static chess.model.Direction.EAST;
import static chess.model.Direction.NORTH;
import static chess.model.Direction.NORTHEAST;
import static chess.model.Direction.NORTHWEST;
import static chess.model.Direction.SOUTH;
import static chess.model.Direction.SOUTHEAST;
import static chess.model.Direction.SOUTHWEST;
import static chess.model.Direction.WEST;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.LimitedMovableStrategy;
import java.util.List;

public final class King extends Piece {

    private static final int KING_MOVE_LIMIT = 1;
    private static final List<Direction> KING_DIRECTION
            = List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);

    public King(Color color, Square square) {
        super(color, square, new LimitedMovableStrategy(KING_DIRECTION, KING_MOVE_LIMIT));
    }

    @Override
    public Point getPoint() {
        return Point.KING;
    }
}
