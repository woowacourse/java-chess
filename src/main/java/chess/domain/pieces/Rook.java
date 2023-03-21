package chess.domain.pieces;

import static chess.domain.math.Direction.DOWN;
import static chess.domain.math.Direction.LEFT;
import static chess.domain.math.Direction.RIGHT;
import static chess.domain.math.Direction.UP;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public void validateDirection(final Direction direction) {
        if (directions.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다. 다시 입력해주세요.");
    }

    @Override
    public void validateDistance(final Position current, final Position target) {

    }
}
