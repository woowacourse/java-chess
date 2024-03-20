package piece;

import model.Camp;
import point.Position;

public class Pawn extends Piece {

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "p";
        }
        return "P";
    }
}
