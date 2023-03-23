package chess.model.piece.pawn;

import static chess.model.position.Direction.SOUTH;
import static chess.model.position.Direction.SOUTH_EAST;
import static chess.model.position.Direction.SOUTH_WEST;

import chess.model.piece.Piece;
import chess.model.position.Direction;
import chess.model.position.Distance;
import java.util.Set;

public class InitialBlackPawn extends Pawn {

    private static final Set<Direction> MOVE_DIRECTIONS = Set.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    private static final int MINIMUM_DISTANCE = 1;
    private static final int MAXIMUM_DISTANCE = 2;

    private final Piece pawn;

    public InitialBlackPawn(final Piece pawn) {
        super(pawn.getColor());
        this.pawn = pawn;
    }

    @Override
    protected boolean isRightDirection(final Direction direction) {
        return MOVE_DIRECTIONS.contains(direction);
    }

    @Override
    protected boolean isRightDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank >= MINIMUM_DISTANCE && rank <= MAXIMUM_DISTANCE;
    }

    @Override
    public Piece update() {
        return pawn;
    }
}
