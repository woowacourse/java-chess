package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Column;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

import java.util.Objects;

public abstract class Piece {
    private final PieceType pieceType;
    private final Team team;
    private final Point point;

    // TODO remove
    private final String initial;
    private final double score;

    protected Piece(PieceType pieceType, Team team, Point point) {
        this.pieceType = pieceType;
        this.team = team;
        this.point = point;

        this.initial = null;
        this.score = 0;
    }

    // TODO remove
    protected Piece(String initial, Team team, Point point, double score) {
        this.initial = initial;
        this.team = team;
        this.point = point;
        this.score = score;
        pieceType = null;
    }

    public abstract Piece move(Point afterPoint);

    public abstract void validateMoveDirection(Direction direction);

    public void validateAttack(Direction direction, Piece piece) {
        try {
            validateMoveDirection(direction);
        } catch (CanNotMoveException e) {
            throw new CanNotAttackException("공격할 수 없는 방향입니다.");
        }

        if (isAlly(piece)) {
            throw new CanNotAttackException("같은 팀을 공격할 수 없습니다.");
        }
    }

    protected boolean isAlly(Piece other) {
        return this.team.equals(other.team);
    }

    public void validateReach(Distance distance) {
    }

    public boolean matchPoint(Point point) {
        return this.point.equals(point);
    }

    public boolean matchColumnPoint(Column column) {
        return point.matchColumn(column);
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
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

    public Team getTeam() {
        return team;
    }

    public Point getPoint() {
        return point;
    }

    public double getScore() {
        return score;
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
