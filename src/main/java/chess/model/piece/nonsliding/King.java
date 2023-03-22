package chess.model.piece.nonsliding;

import chess.model.Color;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import chess.model.position.Distance;
import java.util.Set;

public class King extends NonSlidingPiece {

    private static final Set<Direction> directions = Direction.allDirections();
    private static final int MAXIMUM_DISTANCE = 1;

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    protected boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }

    @Override
    protected boolean isRightDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());
        final int file = Math.abs(distance.file());

        return rank <= MAXIMUM_DISTANCE && MAXIMUM_DISTANCE >= file;
    }
}
