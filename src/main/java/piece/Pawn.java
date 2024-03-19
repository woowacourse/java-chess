package piece;

import model.Camp;
import point.Point;

public class Pawn extends Piece {

    private final Camp camp;
    private Point point;

    public Pawn(final Camp camp, final Point point) {
        this.camp = camp;
        this.point = point;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "p";
        }
        return "P";
    }
}
