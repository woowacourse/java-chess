package chess.domain.RuleImpl;

import chess.domain.Position;

public class Knight implements Rule {
    private static Knight INSTANCE = new Knight();

    private Knight() {

    }

    public static Knight getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 3) && origin.isMoveAnyWhereSub(target, 1);
    }
}
