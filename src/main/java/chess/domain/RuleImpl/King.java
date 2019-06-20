package chess.domain.RuleImpl;

import chess.domain.Position;

public class King implements Rule {
    private static King INSTANCE = new King();
    private static final double SCORE = 0;

    private King() {

    }

    public static King getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return isLengthOne(origin, target) || isLengthTwo(origin, target);
    }

    private boolean isLengthOne(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 1);
    }

    private boolean isLengthTwo(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 2) && origin.isMoveAnyWhereSub(target, 0);
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        return isValidMove(origin, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
