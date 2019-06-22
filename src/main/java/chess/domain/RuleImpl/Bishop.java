package chess.domain.RuleImpl;

import chess.domain.Position;

public class Bishop extends AbstractRule {
    private static Bishop INSTANCE = new Bishop();
    private static final double SCORE = 3;

    private Bishop() {
        super(SCORE);
    }

    public static Bishop getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target);
    }
}
