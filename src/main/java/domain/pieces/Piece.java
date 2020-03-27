package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

public abstract class Piece {
    private final String initial;
    private final Team team;
    private final Point point;
    private final double score;

    protected Piece(String initial, Team team, Point point, double score) {
        this.initial = initial;
        this.team = team;
        this.point = point;
        this.score = score;
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

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }

    public abstract Piece move(Point afterPoint);

    public abstract void canMove(Direction direction);

    public void canAttack(Direction direction, Piece piece) {
        canMove(direction);
        if (isAlly(piece)) {
            throw new CanNotAttackException();
        }
    }

    protected boolean isAlly(Piece other) {
        return this.team == other.team;
    }

    public Team getTeam() {
        return team;
    }

    public Point getPoint() {
        return point;
    }

    public void canReach(Distance distance) {
    }

    public boolean matchPoint(Point point) {
        return this.point.equals(point);
    }

    @Override
    public String toString() {
        return initial;
    }
}
