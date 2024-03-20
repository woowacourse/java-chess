package chess.model;

import java.util.List;

public class Pawn extends Piece {
    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    public Pawn(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        if (canMove(source, distance)) {
            return distance.findPath(source);
        }
        return List.of();
    }

    private boolean canMove(ChessPosition source, Distance distance) {
        if (source.isPawnInitialPosition(side)) {
            return canMoveForwardWith(distance, DISPLACEMENT) ||
                    canMoveForwardWith(distance, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(distance, DISPLACEMENT);
    }

    private boolean canMoveForwardWith(Distance distance, int displacement) {
        return distance.isForward() && distance.hasSame(displacement);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }
}
