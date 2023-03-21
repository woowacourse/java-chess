package chess.domain.pieces;

import static chess.domain.Team.BLACK;
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

    public boolean isMoved() {
        return this.isMoved;
    }

    @Override
    public void validateDistance(final Position current, final Position target) {

    }
}
