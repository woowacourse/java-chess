package piece;

import model.Camp;
import point.Position;

public class Bishop extends Piece {

    public Bishop(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "b";
        }
        return "B";
    }
}
