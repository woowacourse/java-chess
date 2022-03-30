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
import chess.model.piece.strategy.UnlimitedMovableStrategy;
import java.util.List;

public final class Queen extends Piece {

    private static final List<Direction> QUEEN_DIRECTION
            = List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);

    public Queen(Color color, Square square) {
        super(color, square, new UnlimitedMovableStrategy(QUEEN_DIRECTION));
    }

    @Override
    public Point getPoint() {
        return Point.QUEEN;
    }
}
