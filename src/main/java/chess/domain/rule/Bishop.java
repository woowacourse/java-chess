package chess.domain.rule;

import chess.domain.Position;

public class Bishop extends AbstractRule {
    private static Bishop INSTANCE = new Bishop();

    private Bishop() {
        super(Type.BISHOP);
    }

    public static Bishop getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target);
    }
}
