package chess.model.piece;

import static chess.model.Direction.NORTHEAST;
import static chess.model.Direction.NORTHWEST;
import static chess.model.Direction.SOUTHEAST;
import static chess.model.Direction.SOUTHWEST;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.UnlimitedMovableStrategy;
import java.util.List;

public final class Bishop extends Piece {

    private static final List<Direction> BISHOP_DIRECTION = List.of(SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);

    public Bishop(Color color, Square square) {
        super(color, square,
                new UnlimitedMovableStrategy(BISHOP_DIRECTION));
    }

    @Override
    public Point getPoint() {
        return Point.BISHOP;
    }
}
