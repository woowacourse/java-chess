package piece;

import model.Camp;
import point.Position;

public class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "r";
        }
        return "R";
    }
}
