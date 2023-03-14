package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class Pawn extends Piece {

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
