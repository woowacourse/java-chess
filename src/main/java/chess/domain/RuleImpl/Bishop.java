package chess.domain.RuleImpl;

import chess.domain.Position;

public class Bishop implements Rule {

    private static Bishop INSTANCE = new Bishop();


    private Bishop() {

    }

    public static Bishop getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target);
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        return isValidMove(origin, target);
    }
}
