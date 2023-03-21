package chess.domain.pieces;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.NEUTRALITY;
import static chess.domain.math.Direction.DOWN;
import static chess.domain.math.Direction.DOWN_LEFT;
import static chess.domain.math.Direction.DOWN_RIGHT;
import static chess.domain.math.Direction.UP;
import static chess.domain.math.Direction.UP_LEFT;
import static chess.domain.math.Direction.UP_RIGHT;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {

    private static final int MIN_MOVABLE_DISTANCE = 0;
    private static final int MOVABLE_DISTANCE_NORMAL = 1;
    private static final int MOVABLE_MAX_DISTANCE_WHEN_FIRST = 2;

    private final List<Direction> directions;
    private boolean isMoved = false;

    public Pawn(final Team team) {
        super(team);
        validateTeam(team);
        this.directions = initDirections(team);
    }

    @Override
    public void validateDirection(final Direction direction) {
        if (directions.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다. 다시 입력해주세요.");
    }

    private List<Direction> initDirections(final Team team) {
        if (team == BLACK) {
            return new ArrayList<>(List.of(DOWN, DOWN_LEFT, DOWN_RIGHT));
        }
        return new ArrayList<>(List.of(UP, UP_LEFT, UP_RIGHT));
    }

    public void move() {
        this.isMoved = true;
    }

    @Override
    public void validateDistance(final Position current, final Position target) {
        int distance = current.calculateDistance(target);

        if (isMoved) {
            validateOneDistance(distance);
        }
        validateUnderTwoDistance(distance);
    }

    private void validateOneDistance(final int distance) {
        if (distance != MOVABLE_DISTANCE_NORMAL) {
            throw new IllegalArgumentException("폰은 두 번째 이동부터 1칸만 이동할 수 있습니다.");
        }
    }

    private void validateUnderTwoDistance(final int distance) {
        if (distance < MIN_MOVABLE_DISTANCE || distance > MOVABLE_MAX_DISTANCE_WHEN_FIRST) {
            throw new IllegalArgumentException("폰의 첫 번째 이동은 2칸 이내만 가능합니다.");
        }
    }

    public void validateExistPiece(final Piece piece) {
        if (piece.getTeam() != NEUTRALITY) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재합니다.");
        }
    }
}
