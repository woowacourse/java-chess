package chess.domain.pieces.nonsliding;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    protected static final String INVALID_TEAM = "해당 기물은 중립일 수 없습니다.";

    public NonSlidingPiece(final Team team, final List<Direction> directions) {
        super(team, List.copyOf(directions));
    }

    @Override
    protected void validateTeam(final Team team) {
        if (team.isNeutrality()) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }
}
