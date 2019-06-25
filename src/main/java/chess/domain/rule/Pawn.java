package chess.domain.rule;

import chess.domain.Position;
import chess.domain.Rule;

public class Pawn extends Rule {
    public static Pawn FIRST_TOP = new Pawn(-2);
    public static Pawn SECOND_TOP = new Pawn(-1);
    public static Pawn FIRST_BOTTOM = new Pawn(2);
    public static Pawn SECOND_BOTTOM = new Pawn(1);

    private static final int ZERO_VECTOR = 0;

    private final int distance;

    private Pawn(final int distance) {
        super(Type.PAWN);
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
}
