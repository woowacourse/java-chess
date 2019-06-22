package chess.domain.RuleImpl;

import chess.domain.Position;

public abstract class AbstractRule implements Rule {
    private double score;

    AbstractRule(final double score) {
        this.score = score;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return false;
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        return isValidMove(origin, target);
    }

    @Override
    public double getScore() {
        return score;
    }
}
