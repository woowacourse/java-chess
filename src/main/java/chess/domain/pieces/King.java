package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.List;

import static chess.domain.Direction.*;

public class King extends Piece {

    private static final int STEP = 1;

    public King(final Team team) {
        super(team);
        this.directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        validateTeam(team);
        this.type = Type.KING;
    }

    @Override
    public void validateTeam(Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalStateException(INVALID_NOT_EMPTY_TEAM);
        }
    }

    @Override
    public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
        checkStep(pieces.size());
    }

    @Override
    public void checkExistPiece(List<Piece> pieces) {
    }

    private void checkStep(int size) {
        if (size > STEP) {
            throw new IllegalArgumentException("[ERROR] 킹은 한 칸만 움직일 수 있습니다.");
        }
    }
}
