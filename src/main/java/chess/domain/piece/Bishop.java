package chess.domain.piece;

import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;

import chess.domain.board.Direction;
import java.util.List;

public final class Bishop extends AbstractStraightPiece {

    private static final List<Direction> directions = List.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    private static final double POINT = 3.0;

    public Bishop(Color color) {
        super(color, directions);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
