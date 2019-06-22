package chess.domain.RuleImpl;

import chess.domain.Position;

public class Queen extends AbstractRule {
    private static Queen INSTANCE = new Queen();
    private static final double SCORE = 9;

    private Queen() {
        super(SCORE);
    }

    public static Queen getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
    }
}
