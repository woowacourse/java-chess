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

    protected Piece(PieceType pieceType, Team team, Point point) {
        this.pieceType = pieceType;
        this.team = team;
        this.point = point;
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

    public boolean isKindOf(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public String getInitial() {
        return team.caseInitial(pieceType.getInitial());
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
        return pieceType.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType &&
                team == piece.team &&
                Objects.equals(point, piece.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, team, point);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", team=" + team +
                ", point=" + point +
                '}';
    }
}
