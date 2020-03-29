package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.route.Route;
import chess.domain.route.RouteFinder;

import java.util.Objects;

public class Piece implements Placeable {
    private Team team;
    private PieceType pieceType;

    public Piece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public Route findRoute(Position fromPosition, Position toPosition) {
        return RouteFinder.findRoute(fromPosition, toPosition, team, pieceType);
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public String getAcronym() {
        return pieceType.getAcronym(team);
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isOppositeTeam(Team team) {
        return this.team != team;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, pieceType);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", pieceType=" + pieceType +
                '}';
    }
}
