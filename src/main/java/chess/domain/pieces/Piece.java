package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;

public abstract class Piece {

    protected static final String INVALID_TEAM = "[ERROR] 허락되지 않는 팀입니다.";

    protected final Team team;
    protected Name name;

    public Piece(final Team team) {
        this.team = team;
    }

    void validateTeam(final Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }

    public Team getTeam() {
        return this.team;
    }

    abstract public boolean hasDirection(final Direction direction);
}
