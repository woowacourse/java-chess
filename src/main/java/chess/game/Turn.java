package chess.game;

import chess.domain.Team;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Turn {

    private static final Map<Team, Turn> CACHE = new HashMap<>();

    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public Turn next() {
        if (team == Team.WHITE) {
            return CACHE.computeIfAbsent(Team.WHITE, ignore -> new Turn(Team.BLACK));
        }
        return CACHE.computeIfAbsent(Team.BLACK, ignore -> new Turn(Team.WHITE));
    }

    public boolean isCorrectWith(Piece piece) {
        return team == piece.getTeam();
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return getTeam() == turn.getTeam();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam());
    }
}
