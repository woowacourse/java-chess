package chess.domain.piece;

import chess.domain.board.Route;

public class BlackPawn extends Pawn {
    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    boolean isBackward(Route route) {
        return route.isUpside();
    }
}
