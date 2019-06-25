package chess.domain.rule;

import chess.domain.Position;

public class Knight extends AbstractRule {
    private static Knight INSTANCE = new Knight();

    private Knight() {
        super(Type.KNIGHT);
    }

    public static Knight getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 3) && origin.isMoveAnyWhereSub(target, 1);
    }
}
