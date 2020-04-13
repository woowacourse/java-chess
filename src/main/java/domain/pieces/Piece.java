package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.coordinate.Column;
import domain.coordinate.Direction;
import domain.coordinate.Distance;
import domain.coordinate.Coordinate;
import domain.team.Team;

import java.util.Objects;

public abstract class Piece {
    private final PieceType pieceType;
    private final Team team;
    private final Coordinate coordinate;

    protected Piece(PieceType pieceType, Team team, Coordinate coordinate) {
        this.pieceType = pieceType;
        this.team = team;
        this.coordinate = coordinate;
    }

    public abstract Piece move(Coordinate afterCoordinate);

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
        return this.team == other.team;
    }

    public void validateReach(Distance distance) {
    }

    public boolean matchPoint(Coordinate coordinate) {
        return this.coordinate.equals(coordinate);
    }

    public boolean matchColumnPoint(Column column) {
        return coordinate.matchColumn(column);
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public boolean isType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public String getInitial() {
        return team.caseInitial(pieceType.getInitial());
    }

    public int getRowIndex() {
        return coordinate.getRowIndex();
    }

    public int getColumnIndex() {
        return coordinate.getColumnIndex();
    }

    public Team getTeam() {
        return team;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public String getPieceTypeName() {
        return pieceType.getName();
    }

    public String getTeamName() {
        return team.getName();
    }

    public String getCoordinateRepresentation() {
        return coordinate.getRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType &&
                team == piece.team &&
                Objects.equals(coordinate, piece.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, team, coordinate);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", team=" + team +
                ", point=" + coordinate +
                '}';
    }
}
