package chess.domain.piece;

import static chess.domain.board.Direction.EAST;
import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.WEST;

import chess.domain.board.Direction;
import java.util.List;

public final class Rook extends AbstractStraightPiece {

    private static final List<Direction> DIRECTIONS = List.of(NORTH, WEST, SOUTH, EAST);
    private static final double POINT = 5.0;

    public Rook(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
