package chess;

import java.util.Objects;

public final class Turn {
    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public static Turn init() {
        return new Turn(Team.WHITE);
    }

    public boolean isCurrentTeam(Team team) {
        return this.team.equals(team);
    }

    public Turn change() {
        if (team.equals(Team.BLACK)) {
            return new Turn(Team.WHITE);
        }
        return new Turn(Team.BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Turn turn = (Turn) o;
        return team == turn.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
