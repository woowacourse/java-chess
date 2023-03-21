package chess.model.piece.type;

import static chess.model.piece.Camp.BLACK;
import static chess.model.piece.Camp.WHITE;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.SOUTH;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.Map;

public class InitialPawn extends Piece {

    private static final Map<Camp, Direction> moveDirections = Map.of(
            WHITE, NORTH,
            BLACK, SOUTH
    );
    private static final int INITIAL_DISTANCE = 2;

    private final Piece pawn;

    public InitialPawn(final Piece pawn) {
        super(pawn.camp());
        this.pawn = pawn;
    }

    @Override
    public boolean movable(final Distance distance, final Piece target) {
        if (isEmpty(target) && isAvailableDirection(distance)) {
            return true;
        }
        return pawn.movable(distance, target);
    }

    private boolean isEmpty(final Piece target) {
        return !target.isNotPassable();
    }

    @Override
    protected boolean isAvailableDirection(final Distance distance) {
        final Direction availableDirection = moveDirections.get(camp());

        return isAvailableDistance(distance) && distance.matchByDirection(availableDirection);
    }

    private boolean isAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank == Pawn.MOVE_DISTANCE || rank == INITIAL_DISTANCE;
    }


    @Override
    public Piece pick() {
        return this.pawn;
    }
}
