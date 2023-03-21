package chess.domain.pieces;

import static chess.domain.math.Direction.KNIGHT;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public final class Knight extends Piece {

    private final List<Direction> directions = List.of(KNIGHT);

    public Knight(final Team team) {
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
