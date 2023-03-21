package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;

public abstract class Piece {

    protected static final String INVALID_TEAM = "[ERROR] 허락되지 않는 팀입니다.";

    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    void validateTeam(final Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }

    abstract public void validateDirection(final Direction direction);

    abstract public void validateDistance(final Position current, final Position target);

    public void validateSameTeam(final Piece otherPiece) {
        if (this.getTeam() == otherPiece.getTeam()) {
            throw new IllegalArgumentException("해당 위치에 같은 팀이 존재합니다. 이동할 수 없습니다.");
        }
    }

    public Team getTeam() {
        return this.team;
    }
}
