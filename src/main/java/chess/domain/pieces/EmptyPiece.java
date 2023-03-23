package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.component.Name;

public final class EmptyPiece extends Piece {

    static final String INVALID_TEAM = "[ERROR] EmptyPiece 의 팀은 NEUTRALITY 여야 합니다.";
    private static final String EMPTY_NAME = ".";

    public EmptyPiece(final Team team) {
        super(team);
        validateTeam(team);
        initialName();
    }

    private void initialName() {
        this.name = new Name(EMPTY_NAME);
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
