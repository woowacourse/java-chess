package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.Position;
import chess.domain.pieces.component.Team;
import chess.domain.pieces.component.Type;

import java.util.List;

import static chess.domain.Direction.*;

public class Rook extends Piece {

    public Rook(final Team team, final Type type) {
        super(team, type);
        this.directions = List.of(UP, DOWN, LEFT, RIGHT);
        validateTeam(team);
        this.type = Type.ROOK;
    }

    @Override
    public void validateTeam(Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalStateException(INVALID_NOT_EMPTY_TEAM);
        }
    }

    @Override
    public void checkStep(Position currentPosition, Direction direction, List<Piece> pieces) {
    }

}
