package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.point.Column;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

import java.util.Objects;

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

    public boolean isTeam(Team team) {
        return this.team.equals(team);
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

    public double getScore() {
        return score;
    }

    public void canReach(Distance distance) {
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean matchPoint(Point point) {
        return this.point.equals(point);
    }

    public boolean matchColumnPoint(Column column) {
        return this.point.getColumn().equals(column);
    }

    public boolean isNotPawn() {
        return !(this instanceof Pawn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Double.compare(piece.score, score) == 0 &&
                Objects.equals(initial, piece.initial) &&
                team == piece.team &&
                Objects.equals(point, piece.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initial, team, point, score);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "initial='" + initial + '\'' +
                ", team=" + team +
                ", point=" + point +
                ", score=" + score +
                '}';
    }
}
