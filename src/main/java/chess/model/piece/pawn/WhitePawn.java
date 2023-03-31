package chess.model.piece.pawn;

import static chess.model.position.Direction.NORTH;
import static chess.model.position.Direction.NORTH_EAST;
import static chess.model.position.Direction.NORTH_WEST;

import chess.model.Color;
import chess.model.piece.Piece;
import chess.model.position.Direction;
import chess.model.position.Distance;
import java.util.Set;

public class WhitePawn extends Pawn {

    private static final Set<Direction> MOVE_DIRECTIONS = Set.of(NORTH, NORTH_EAST, NORTH_WEST);
    private static final int MINIMUM_DISTANCE = 1;

    public WhitePawn(final Color color) {
        super(color);
    }

    @Override
    protected boolean isRightDirection(final Direction direction) {
        return MOVE_DIRECTIONS.contains(direction);
    }

    @Override
    protected boolean isRightDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank == MINIMUM_DISTANCE;
    }

    @Override
    public Piece update() {
        return this;
    }
}
