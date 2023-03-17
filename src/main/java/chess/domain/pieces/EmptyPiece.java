package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;

public final class EmptyPiece extends Piece {

    static final String INVALID_TEAM = "[ERROR] EmptyPiece의 팀은 NOTHING 여야 합니다. 입력값: ";

    public EmptyPiece(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    protected void validateTeam(final Team team) {
        if (team != Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_TEAM + team);
        }
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return false;
    }
}
