package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.math.Direction.*;

public class Pawn extends Piece {
    private static final int TWO_STEP = 2;
    private static final int ONE_STEP = 1;
    private static final String PAWN_NAME = "P";

    private final List<Direction> directions;

    public Pawn(final Team team) {
        super(team);
        validateTeam(team);
        this.directions = initDirections(team);
        initialName(team);
    }

    private void initialName(Team team) {
        if (team == Team.BLACK) {
            this.name = new Name(PAWN_NAME);
            return;
        }
        this.name = new Name(PAWN_NAME.toLowerCase());
    }

    private List<Direction> initDirections(final Team team) {
        if (team == BLACK) {
            return new ArrayList<>(List.of(DOWN, DOWN_LEFT, DOWN_RIGHT));
        }
        return new ArrayList<>(List.of(UP, UP_LEFT, UP_RIGHT));
    }

    public boolean canMoveStep(Position current, Position target) {
        if (calculateStep(current, target) == TWO_STEP) {
            checkFirstMove(current);
            return true;
        }
        return calculateStep(current, target) == ONE_STEP;
    }

    private int calculateStep(Position current, Position target) {
        int rankGap = Math.abs(current.getRank() - target.getRank());
        int fileGap = Math.abs(current.getFile() - target.getFile());
        return Math.max(rankGap, fileGap);
    }

    private void checkFirstMove(Position current) {
        if (this.team == BLACK) {
            if (!(current.getRank() == 7)) {
                throw new IllegalArgumentException("폰은 처음 이동만 두칸이 가능합니다.");
            }
        }
        if (this.team == WHITE) {
            if (!(current.getRank() == 2)) {
                throw new IllegalArgumentException("폰은 처음 이동만 두칸이 가능합니다.");
            }
        }
    }

    public boolean isUpOrDown(final Direction direction) {
        return direction == Direction.DOWN || direction == Direction.UP;
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
