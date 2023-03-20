package chess.domain;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();

    static {
        addPawns(blackPawns, Side.BLACK);
        addPawns(whitePawns, Side.WHITE);
    }

    private boolean atInitialPosition;

    private Pawn(Side side) {
        super(side);
        atInitialPosition = true;
    }

    private static void addPawns(final List<Pawn> pawns, final Side side) {
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(side));
        }
    }

    public static List<Pawn> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackPawns);
        }
        return List.copyOf(whitePawns);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return canMoveForward(from, to, piece) ||
                isCatchable(from, to, piece);
    }

    private boolean canMoveForward(final Square from, final Square to, final Piece piece) {
        boolean targetInMovableRange = isMovableRange(from, to, side);
        if (atInitialPosition) {
            targetInMovableRange = isInitialMovableRange(from, to, side);
        }
        return piece.isEmpty() && targetInMovableRange;
    }

    private boolean isInitialMovableRange(final Square from, final Square to, final Side side) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        return from.isBackOf(to, side) &&
                (verticalDistance == 1 || verticalDistance == 2) &&
                from.calculateHorizontalDistance(to) == 0;
    }

    private boolean isMovableRange(final Square from, final Square to, final Side side) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        return from.isBackOf(to, side) &&
                verticalDistance == 1 &&
                from.calculateHorizontalDistance(to) == 0;
    }

    private boolean isCatchable(final Square from, final Square to, final Piece piece) {
        return this.isOppositeSide(piece) && isCatchableRange(from, to, side);
    }

    public boolean isCatchableRange(final Square from, final Square to, final Side side) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return from.isBackOf(to, side) &&
                verticalDistance == 1 &&
                horizontalDistance == 1;
    }

    public void move() {
        atInitialPosition = false;
    }
}
