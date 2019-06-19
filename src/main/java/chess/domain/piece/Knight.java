package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Piece;
import chess.domain.Team;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;
import static chess.domain.Direction.NW;

public class Knight extends Piece {
    private static final String NAME = "n";
    private static final List<Direction> MOVABLE_DIRECTION = Arrays.asList(N, NE, E, SE, S, SW, W, NW);

    public Knight(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.direction(target);

            if (MOVABLE_DIRECTION.stream().anyMatch(movable -> movable == direction)) {
                throw new IllegalArgumentException("움직일 수 있는 방향이 아닙니다.");
            }

            if (source.distance(target) != 3) {
                throw new IllegalArgumentException("움직일 수 있는 거리가 아닙니다.");
            }

            return true;
        });
    }

    public String getName() {
        return NAME;
    }
}
