package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class Knight extends Piece {
    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
