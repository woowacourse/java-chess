package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class Rook extends Piece {
    public Rook(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
