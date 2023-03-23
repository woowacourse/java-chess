package chess.domain.pieces;

import chess.domain.pieces.component.Team;
import chess.domain.Direction;
import chess.domain.pieces.component.Name;

import java.util.List;

import static chess.domain.Direction.*;

public class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";

    private final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    public Queen(final Team team) {
        super(team);
        validateTeam(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(QUEEN_NAME);
            return;
        }
        this.name = new Name(QUEEN_NAME.toLowerCase());
    }

    @Override
    public boolean hasDirection(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public void validateTeam(Team team) {
        if (team == Team.NEUTRALITY) {
            throw new IllegalStateException("중립팀은 emptyPiece 만 가능합니다");
        }
    }
}
