package piece;

import model.Camp;
import point.Position;

public class Knight extends Piece {

    public Knight(final Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMovable(Position currentPosition, Position nextPosition) {
        return false;
    }

    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "n";
        }
        return "N";
    }
}
