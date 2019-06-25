package chess.domain;

import java.util.Objects;

public abstract class Piece {
    private final Team team;
    private MoveRule moveRule;

    public Piece(Team team, MoveRule moveRule) {
        this.team = team;
        this.moveRule = moveRule;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isTurn(Turn turn) {
        return turn.isTurn(team);
    }

    public boolean canMove(Position source, Position target) {
        return moveRule.check(source, target);
    }

    public double score(final int count) {
        return count * getScore();
    }

    public abstract String getName();

    public abstract double getScore();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return team == piece.team &&
                Objects.equals(moveRule, piece.moveRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, moveRule);
    }
}