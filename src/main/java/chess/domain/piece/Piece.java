package chess.domain.piece;

import chess.domain.Point;
import java.util.Objects;

public abstract class Piece {

    private final String name;
    private final Team team;

    public Piece(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public static Piece bishopFrom(Team team) {
        return new Bishop(team);
    }

    public static Piece kingFrom(Team team) {
        return new King(team);
    }

    public static Piece knightFrom(Team team) {
        return new Knight(team);
    }

    public static Piece pawnFrom(Team team) {
        return new Pawn(team);
    }

    public static Piece queenFrom(Team team) {
        return new Queen(team);
    }

    public static Piece rookFrom(Team team) {
        return new Rook(team);
    }

    public abstract boolean isMovable(Point currentPoint, Point nextPoint);

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public String getName() {
        if (team.isWhite()) {
            return name.toLowerCase();
        }
        return name;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(name, piece.name) && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team);
    }
}
