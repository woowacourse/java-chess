package domain.point;

import domain.pieces.Piece;
import java.util.Map;

public class MovePoint {
    private final Point from;
    private final Point to;

    public MovePoint(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public boolean isSameTeam(Map<Point, Piece> pieces) {
        return pieces.get(from).getTeam() == pieces.get(to).getTeam();
    }

    public int getRowDistance() {
        return from.getRowDistance(to);
    }

    public int getColumnDistance() {
        return from.getColumnDistance(to);
    }

    public int getFromRowIndex() {
        return from.getRowIndex();
    }

    public int getFromColumnIndex() {
        return from.getColumnIndex();
    }

    public boolean canMovePoint(int row, int column) {
        return row < to.getRowIndex() && column < to.getColumnIndex();
    }
}
