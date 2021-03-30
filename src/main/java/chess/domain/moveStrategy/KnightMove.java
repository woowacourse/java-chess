package chess.domain.moveStrategy;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;

public class KnightMove extends SingleMove {
    @Override
    public List<Direction> directions() {
        return Arrays.asList(
                new Direction(2, 1),
                new Direction(2, -1),
                new Direction(-2, 1),
                new Direction(-2, -1),
                new Direction(1, 2),
                new Direction(1, -2),
                new Direction(-1, 2),
                new Direction(-1, -2)
        );
    }
}
