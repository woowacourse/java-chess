package chess.domain.piece;

import chess.domain.Camp;

public final class Queen extends Piece{
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
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
        return true;
    }

    @Override
    public boolean isRook() {
        return false;
    }

}
