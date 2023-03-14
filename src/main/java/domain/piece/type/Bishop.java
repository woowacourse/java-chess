package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class Bishop extends Piece {
    public Bishop(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
