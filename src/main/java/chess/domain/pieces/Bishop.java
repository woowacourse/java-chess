package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;

import java.util.List;

import static chess.domain.math.Direction.*;

public class Bishop extends Piece {

    private static final String BISHOP_NAME = "B";
    private final List<Direction> directions = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    public Bishop(final Team team) {
        super(team);
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
    public boolean hasDirection(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public void validateTeam(Team team) {
        if(team == Team.NEUTRALITY){
            throw new IllegalStateException("중립팀은 emptyPiece 만 가능합니다");
        }
    }
}
