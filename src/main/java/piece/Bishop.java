package piece;

import model.Camp;
import point.Point;

public class Bishop extends Piece {

    private final Camp camp;
    private Point point;


    public Bishop(final Camp camp, Point point) {
        this.camp = camp;
        this.point = point;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "b";
        }
        return "B";
    }
}
