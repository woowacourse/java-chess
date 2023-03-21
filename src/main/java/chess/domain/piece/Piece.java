package chess.domain.piece;

import chess.domain.team.Team;

import java.util.Objects;

public abstract class Piece {

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public abstract boolean movable(final Direction direction);

    public abstract boolean movableByCount(final int count);

    public boolean isAttack(final Direction direction, final Team team) {
        return movable(direction);
    }

    public boolean isSameTeam(final Team team) {
        return this.team.equals(team);
    }

    public Team team() {
        return team;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
