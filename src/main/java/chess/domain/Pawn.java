package chess.domain;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final List<Direction> MOVABLE_DIRECTION = Arrays.asList(N, E, S, W);

    public Pawn(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.direction(target);

            if (MOVABLE_DIRECTION.stream().noneMatch(movable -> movable == direction)) {
                throw new IllegalArgumentException("움직일 수 있는 방향이 아닙니다.");
            }

            if (source.distance(target) != 1) {
                throw new IllegalArgumentException("움직일 수 있는 거리가 아닙니다.");
            }

            return true;
        });
    }

    @Override
    public String getName() {
        return NAME;
    }
}
