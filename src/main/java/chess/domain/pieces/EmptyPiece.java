package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Name;
import chess.domain.pieces.component.Team;

import java.util.ArrayList;
import java.util.List;

public final class EmptyPiece extends Piece {

    private static final String EMPTY_NAME = ".";

    public EmptyPiece(final Team team) {
        super(team);
        this.directions = new ArrayList<>();
        validateTeam(team);
        initialName();
    }

    private void initialName() {
        this.name = new Name(EMPTY_NAME);
    }

    @Override
    public void validateTeam(final Team team) {
        if (team != Team.NEUTRALITY) {
            throw new IllegalArgumentException(INVALID_EMPTY_TEAM);
        }
    }

    @Override
    public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
        throw new IllegalArgumentException("[ERROR] 이동 할 기물이 없습니다.");
    }

    @Override
    public void checkExistPiece(List<Piece> pieces) {
    }
}
