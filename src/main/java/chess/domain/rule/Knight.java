package chess.domain.rule;

import chess.domain.Position;
import chess.domain.Rule;

public class Knight extends Rule {
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
