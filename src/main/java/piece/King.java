package piece;

import model.Camp;
import point.Position;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp);
    }


    @Override
    void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "k";
        }
        return "K";
    }
}
