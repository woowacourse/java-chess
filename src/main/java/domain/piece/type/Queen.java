package domain.piece.type;

import domain.piece.Camp;
import domain.piece.Piece;

public class Queen extends Piece {
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
