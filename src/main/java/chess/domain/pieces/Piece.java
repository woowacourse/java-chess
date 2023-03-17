package chess.domain.pieces;

import chess.domain.Pattern;
import chess.domain.Team;

public abstract class Piece {

    protected static final String INVALID_TEAM = "[ERROR] 허락되지 않는 팀입니다.";

    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    abstract public boolean hasPattern(final Pattern pattern);

    void validateTeam(final Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }

    public Team getTeam() {
        return this.team;
    }
}
