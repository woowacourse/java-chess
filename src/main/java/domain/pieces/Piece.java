package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public abstract class Piece {
    private final String initial;
    private final Team team;
    private final Point point;

    protected Piece(String initial, Team team, Point point) {
        this.initial = initial;
        this.team = team;
        this.point = point;
    }

    public String getInitial() {
        return team.caseInitial(initial);
    }

    public int getRowIndex() {
        return point.getRowIndex();
    }

    public int getColumnIndex() {
        return point.getColumnIndex();
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return initial;
    }
}
