package chess.model.piece.type;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> availableDirections = new ArrayList<>();
    private static final int MINIMUM_DISTANCE = 0;
    private static final int MAXIMUM_DISTANCE = 1;

    static {
        availableDirections.addAll(Direction.DIAGONAL);
        availableDirections.addAll(Direction.ORTHOGONAL);
    }

    public King(final Camp camp) {
        super(camp);
    }

    @Override
    public boolean isAvailableDirection(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        return isMovableDirection(distance);
    }

    private boolean isMovableDirection(final Distance distance) {
        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());
        final int file = Math.abs(distance.file());

        return (rank < MINIMUM_DISTANCE || rank > MAXIMUM_DISTANCE) ||
                (file < MINIMUM_DISTANCE || file > MAXIMUM_DISTANCE);
    }
}
