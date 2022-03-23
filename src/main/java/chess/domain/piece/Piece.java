package chess.domain.piece;

import chess.domain.Camp;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public final boolean isBlack(){
        return this.camp == Camp.BLACK;
    }
}
