package chess.domain.RuleImpl;

import chess.domain.Position;

public enum Pawn implements Rule {
    FIRST_TOP(-2),
    SECOND_TOP(-1),
    FIRST_BOTTOM(2),
    SECOND_BOTTOM(1);

    public static final int ZERO_VECTOR = 0;

    private final int distance;

    Pawn(final int distance) {
        this.distance = distance;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        final int vector = origin.vectorOfRow(target);
        return isBottomMovable(vector) || isTopMovable(vector) && origin.isPerpendicular(target);
    }

    private boolean isTopMovable(final int vector) {
        return this.distance <= vector && vector < ZERO_VECTOR;
    }

    private boolean isBottomMovable(final int vector) {
        return this.distance >= vector && vector > ZERO_VECTOR;
    }

}
