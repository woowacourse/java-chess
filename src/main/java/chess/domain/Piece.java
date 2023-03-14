package chess.domain;

import java.util.Objects;

public class Piece {
    private final Type type;
    private final Team team;

    public Piece(Type type, Team team) {
        this.type = type;
        this.team = team;
    }

    public Type getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team);
    }
}
