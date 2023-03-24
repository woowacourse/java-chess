package chess.domain;

import java.util.Objects;

public class Turn {

    private static final int NUMBER_OF_TEAM = 2;

    private final int turn;

    public Turn() {
        this.turn = 0;
    }

    private Turn(int turn) {
        this.turn = turn;
    }

    public Turn next() {
        return new Turn(turn + 1);
    }

    public Team findCurrentTeam() {
        if (turn % NUMBER_OF_TEAM == 0) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public Team findCurrentEnemyTeam() {
        if (turn % NUMBER_OF_TEAM == 0) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Turn other = (Turn) o;
        return turn == other.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
