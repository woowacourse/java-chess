package chess.domain.RuleImpl;

import chess.domain.Position;

public class Rook implements Rule {

    private static Rook INSTANCE = new Rook();
    private static final double SCORE = 5;

    private Rook() {

    }

    public static Rook getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isLevel(target) || origin.isPerpendicular(target);
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
