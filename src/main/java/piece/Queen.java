package piece;

import model.Camp;
import point.Position;

public class Queen extends Piece {

    public Queen(final Camp camp) {
        super(camp);
    }

    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public boolean canMovable(Position currentPosition, Position nextPosition) {
        return false;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "q";
        }
        return "Q";
    }
}
