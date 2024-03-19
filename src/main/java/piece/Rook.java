package piece;

import model.Camp;
import point.Point;

public class Rook extends Piece {

    private final Camp camp;
    private Point point;

    public Rook(final Camp camp, final Point point) {
        this.camp = camp;
        this.point = point;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "r";
        }
        return "R";
    }
}
