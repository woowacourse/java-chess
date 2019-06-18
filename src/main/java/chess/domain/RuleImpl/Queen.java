package chess.domain.RuleImpl;

import chess.domain.Position;

public class Queen implements Rule {
    private static Queen INSTANCE = new Queen();

    private Queen() {

    }

    public static Queen getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
    }
}
