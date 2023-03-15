package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions = Direction.king();
    private static final int MAXIMUM_DISTANCE = 1;

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean movable(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }

        return isRightDirection(distance);
    }

    private boolean isRightDirection(final Distance distance) {
        return directions.stream()
                .anyMatch(direction -> direction.match(distance.rank(), distance.file()));
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());
        final int file = Math.abs(distance.file());

        return rank > MAXIMUM_DISTANCE || MAXIMUM_DISTANCE < file;
    }
}
