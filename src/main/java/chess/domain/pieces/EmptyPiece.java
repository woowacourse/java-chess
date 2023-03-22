package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;

public final class EmptyPiece extends Piece {

    static final String INVALID_TEAM = "[ERROR] EmptyPiece 의 팀은 NEUTRALITY 여야 합니다. 입력값: ";
    private static final String EMPTY_NAME = ".";

    public EmptyPiece(final Team team) {
        super(team);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(EMPTY_NAME);
            return;
        }
        this.name = new Name(EMPTY_NAME.toLowerCase());
    }

    @Override
    public void validateTeam(final Team team) {
        if (team != Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_TEAM + team);
        }
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return false;
    }
}
