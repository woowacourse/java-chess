package chess.domain.rule;

import chess.domain.Position;
import chess.domain.Rule;

public class Queen extends Rule {
    private static Queen INSTANCE = new Queen();

    private Queen() {
        super(Type.QUEEN);
    }

    public static Queen getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
    }
}
