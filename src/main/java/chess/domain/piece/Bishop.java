package chess.domain.piece;

import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;

import chess.domain.board.Direction;
import java.util.List;

public final class Bishop extends AbstractStraightPiece {

    private static final List<Direction> DIRECTIONS = List.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    private static final double POINT = 3.0;
    private static final String output = "B";

    public Bishop(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public String getOutput() {
        if (color == Color.WHITE) {
            return output.toLowerCase();
        }
        return output;
    }
}
