package chess.domain.piece;

import chess.domain.Camp;

public final class King extends Piece {
    public King(Camp camp) {
        super(camp);
    }


    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

}
