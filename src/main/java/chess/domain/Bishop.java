package chess.domain;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Bishop extends Piece {
    private static final String NAME = "b";
    private static final List<Direction> MOVABLE_DIRECTION = Arrays.asList(NE, SE, SW, NW);

    public Bishop(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.direction(target);

            if (MOVABLE_DIRECTION.stream().noneMatch(movable -> movable == direction)) {
                throw new IllegalArgumentException("움직일 수 있는 방향이 아닙니다.");
            }

            return true;
        });
    }

    public String getName() {
        return NAME;
    }
}
