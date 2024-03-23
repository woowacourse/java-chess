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
        return Bishop.from(team);
    }

    public static Piece kingFrom(Team team) {
        return King.from(team);
    }

    public static Piece knightFrom(Team team) {
        return Knight.from(team);
    }

    public static Piece pawnFrom(Team team) {
        return Pawn.from(team);
    }

    public static Piece queenFrom(Team team) {
        return Queen.from(team);
    }

    public static Piece rookFrom(Team team) {
        return Rook.from(team);
    }

    public static Piece empty() {
        return Empty.getEmpty();
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
