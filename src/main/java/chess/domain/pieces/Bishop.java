package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Name;
import chess.domain.pieces.component.Team;

import java.util.List;

import static chess.domain.Direction.*;

public class Bishop extends Piece {

    private static final String BISHOP_NAME = "B";

    public Bishop(final Team team) {
        super(team);
        this.directions = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(BISHOP_NAME);
            return;
        }
        this.name = new Name(BISHOP_NAME.toLowerCase());
    }

    @Override
    public void validateTeam(Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalStateException(INVALID_NOT_EMPTY_TEAM);
        }
    }

    @Override
    public void checkStep(Position currentPiece, Direction direction, List<Piece> pieces) {
    }

    @Override
    public void checkExistPiece(List<Piece> pieces) {
        for (int i = 0; i < pieces.size() - 1; i++) {
            if (pieces.get(i).getTeam() != Team.NEUTRALITY) {
                throw new IllegalArgumentException("[ERROR] 경로에 기물이 존재합니다.");
            }
        }
    }
}
