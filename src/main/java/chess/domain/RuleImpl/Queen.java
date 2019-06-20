package chess.domain.RuleImpl;

import chess.domain.Position;

public class Queen implements Rule {
    private static Queen INSTANCE = new Queen();
    private static final double SCORE = 9;

    private Queen() {

    }

    public static Queen getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
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
