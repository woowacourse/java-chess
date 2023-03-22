package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;

import java.util.List;

import static chess.domain.math.Direction.*;

public class King extends Piece {

    private static final int STEP = 1;
    private static final String KING_NAME = "K";

    private final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    public King(final Team team) {
        super(team);
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

    public boolean canMoveStep(Position current, Position target) {
        return calculateStep(current, target) <= STEP;
    }

    private int calculateStep(Position current, Position target) {
        int rankGap = Math.abs(target.getRank() - current.getRank());
        int fileGap = Math.abs(target.getFile() - current.getFile());
        return Math.max(rankGap, fileGap);
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
