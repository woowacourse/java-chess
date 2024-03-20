package piece;

import java.util.Set;
import model.Camp;
import point.Position;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp);
    }


    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        return null;
    }

    @Override
    protected boolean canMovable(Position currentPosition, Position nextPosition) {
        return false;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "k";
        }
        return "K";
    }
}
