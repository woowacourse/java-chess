package chess.console.domain.piece;

import static chess.console.domain.board.Direction.EAST;
import static chess.console.domain.board.Direction.NORTH;
import static chess.console.domain.board.Direction.NORTH_EAST;
import static chess.console.domain.board.Direction.NORTH_WEST;
import static chess.console.domain.board.Direction.SOUTH;
import static chess.console.domain.board.Direction.SOUTH_EAST;
import static chess.console.domain.board.Direction.SOUTH_WEST;
import static chess.console.domain.board.Direction.WEST;

import chess.console.domain.board.Direction;
import java.util.List;

public final class Queen extends AbstractStraightPiece {

    private static final List<Direction> DIRECTIONS = List
            .of(NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    private static final double POINT = 9.0;

    public Queen(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
