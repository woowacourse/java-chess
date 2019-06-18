package chess.domain.RuleImpl;

import chess.domain.Position;

public class Empty implements Rule {
    private static Empty INSTANCE = new Empty();

    private Empty() {

    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return false;
    }
}
