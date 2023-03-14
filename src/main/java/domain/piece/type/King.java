package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class King extends Piece {
    public King(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
