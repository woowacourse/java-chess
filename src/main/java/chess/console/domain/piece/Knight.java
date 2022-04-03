package chess.console.domain.piece;

import static chess.console.domain.board.Direction.EAST_NORTH_EAST;
import static chess.console.domain.board.Direction.EAST_SOUTH_EAST;
import static chess.console.domain.board.Direction.NORTH_NORTH_EAST;
import static chess.console.domain.board.Direction.NORTH_NORTH_WEST;
import static chess.console.domain.board.Direction.SOUTH_SOUTH_EAST;
import static chess.console.domain.board.Direction.SOUTH_SOUTH_WEST;
import static chess.console.domain.board.Direction.WEST_NORTH_WEST;
import static chess.console.domain.board.Direction.WEST_SOUTH_WEST;

import chess.console.domain.board.Direction;
import java.util.List;

public final class Knight extends AbstractOncePiece {

    private static final List<Direction> DIRECTIONS = List.of(
            NORTH_NORTH_EAST,
            NORTH_NORTH_WEST,
            EAST_NORTH_EAST,
            EAST_SOUTH_EAST,
            SOUTH_SOUTH_EAST,
            SOUTH_SOUTH_WEST,
            WEST_SOUTH_WEST,
            WEST_NORTH_WEST
    );
    private static final double POINT = 2.5;

    public Knight(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
