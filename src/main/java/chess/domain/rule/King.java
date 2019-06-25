package chess.domain.rule;

import chess.domain.Position;
import chess.domain.Rule;

public class King extends Rule {
    private static King INSTANCE = new King();

    private King() {
        super(Type.KING);
    }

    public static King getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return isLengthOne(origin, target) || isLengthTwo(origin, target);
    }

    private boolean isLengthOne(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 1);
    }

    private boolean isLengthTwo(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 2) && origin.isMoveAnyWhereSub(target, 0);
    }
}
