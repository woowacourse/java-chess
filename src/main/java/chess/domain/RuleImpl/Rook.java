package chess.domain.RuleImpl;

import chess.domain.Position;

public class Rook implements Rule {

    private static Rook INSTANCE = new Rook();


    private Rook() {

    }

    public static Rook getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isLevel(target) || origin.isPerpendicular(target);
    }

}
