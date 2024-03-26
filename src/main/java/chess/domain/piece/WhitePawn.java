package chess.domain.piece;

import chess.domain.board.Route;

public class WhitePawn extends Pawn {
    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    boolean isBackward(Route route) {
        return route.isDownside();
    }
}
