package chess.domain.RuleImpl;

import chess.domain.Position;

public enum Pawn implements Rule {
    FIRST_TOP(-2),
    SECOND_TOP(-1),
    FIRST_BOTTOM(2),
    SECOND_BOTTOM(1);

    public static final int ZERO_VECTOR = 0;
    private static final double SCORE = 1;
    public static final double HALF_SCORE = SCORE / 2;
    public static final String NAME = "PAWN";

    private final int distance;

    Pawn(final int distance) {
        this.distance = distance;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        final int vector = origin.vectorOfRow(target);
        return origin.isPerpendicular(target) && (isBottomMovable(vector) || isTopMovable(vector));
    }

    private boolean isTopMovable(final int vector) {
        return this.distance <= vector && vector < ZERO_VECTOR;
    }

    private boolean isBottomMovable(final int vector) {
        return this.distance >= vector && vector > ZERO_VECTOR;
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        if (!origin.isDiagonal(target)) {
            return false;
        }
        final int vector = origin.vectorOfRow(target);
        return origin.sumRowAndColumn(target) == 2 && isSameSign(vector);
    }

    private boolean isSameSign(final int vector) {
        return Integer.compare(0, this.distance) == Integer.compare(0, vector);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
