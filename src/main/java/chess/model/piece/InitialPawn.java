package chess.model.piece;

import chess.model.position.Distance;
import java.util.List;

public class InitialPawn extends Piece {

    private static final List<Direction> white = Direction.whitePawn();
    private static final List<Direction> black = Direction.blackPawn();
    private static final int MINIMUM_DISTANCE = 1;
    private static final int MAXIMUM_DISTANCE = 2;

    private final Piece pawn;

    public InitialPawn(final Piece pawn) {
        super(pawn.color, pawn.type);
        this.pawn = pawn;
    }

    @Override
    public Piece pick() {
        return pawn;
    }

    @Override
    public boolean movable(final Distance distance) {
        if (isUnAvailableDistance(distance)) {
            return false;
        }
        return checkMovableByColor(distance);
    }

    private boolean isUnAvailableDistance(final Distance distance) {
        final int rank = Math.abs(distance.rank());

        return rank < MINIMUM_DISTANCE || MAXIMUM_DISTANCE < rank;
    }

    private boolean checkMovableByColor(final Distance distance) {
        if (PieceColor.WHITE.equals(this.color)) {
            return isMovableByColor(white, distance);
        }

        return isMovableByColor(black, distance);
    }

    private boolean isMovableByColor(final List<Direction> target, final Distance distance) {
        return target.stream()
                .anyMatch(direction -> direction.match(distance.file(), distance.rank()));
    }
}
