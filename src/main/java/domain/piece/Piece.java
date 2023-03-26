package domain.piece;

import domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final String name;
    private final Team team;
    private final double score;

    Piece(final String name, final Team team, final double score) {
        this.name = name;
        this.team = team;
        this.score = score;
    }

    public abstract List<Position> getInitialBlackPositions();

    public abstract List<Position> getInitialWhitePositions();

    public abstract boolean isMovable(final Position source, final Position destination);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public boolean isCapturable(final Position source, final Position destination) {
        return isMovable(source, destination);
    }

    public boolean isBlack() {
        return team.equals(Team.BLACK);
    }

    public boolean isWhite() {
        return team.equals(Team.WHITE);
    }

    public boolean isTeam(Team team) {
        return this.team.equals(team);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        final Piece piece = (Piece) o;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
