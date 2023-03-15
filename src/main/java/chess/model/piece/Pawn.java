package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> white = Direction.whitePawn();
    private static final List<Direction> black = Direction.blackPawn();
    private static final int MINIMUM_DISTANCE = 1;
    private static final int MAXIMUM_DISTANCE = 2;

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    boolean movable(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        if (PieceColor.WHITE.equals(this.color)) {
            return isMovableByColor(white, distance);
        }

        return isMovableByColor(black, distance);
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int file = Math.abs(distance.file());

        return file < MINIMUM_DISTANCE  || MAXIMUM_DISTANCE < file;
    }

    private boolean isMovableByColor(final List<Direction> target, final Distance distance) {
        return target.stream()
                .anyMatch(direction -> direction.match(distance.rank(), distance.file()));
    }
}
