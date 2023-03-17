package chess.domain.pieces;

import chess.domain.Pattern;
import chess.domain.Team;

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
    public boolean hasPattern(final Pattern pattern) {
        return false;
    }
}
