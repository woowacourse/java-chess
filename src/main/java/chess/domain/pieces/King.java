package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Name;
import chess.domain.pieces.component.Team;

import java.util.List;

import static chess.domain.Direction.*;

public class King extends Piece {

    private static final int STEP = 1;
    private static final String KING_NAME = "K";

    public King(final Team team) {
        super(team);
        this.directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(KING_NAME);
            return;
        }
        this.name = new Name(KING_NAME.toLowerCase());
    }

    @Override
    public void validateTeam(Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalStateException("중립팀은 emptyPiece 만 가능합니다");
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
