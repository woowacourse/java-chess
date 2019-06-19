package chess.domain;

import java.util.Objects;

public abstract class Piece {
    private boolean life;
    private final Team team;
    private MoveRule moveRule;

    public Piece(Team team, MoveRule moveRule) {
        this.life = true;
        this.team = team;
        this.moveRule = moveRule;
    }

    public boolean isLife() {
        return this.life;
    }

    public boolean isBlackTeam() {
        return this.team == Team.BLACK;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean canMove(Position source, Position target) {
        return moveRule.check(source, target);
    }

    public abstract String getName();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return life == piece.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(life);
    }
}
