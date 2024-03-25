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

    public abstract boolean isMovable(Point departure, Point destination);

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
