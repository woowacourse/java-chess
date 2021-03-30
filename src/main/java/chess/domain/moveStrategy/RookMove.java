package chess.domain.moveStrategy;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;

public class RookMove extends MultipleMove {
    @Override
    public List<Direction> directions() {
        return Arrays.asList(
                new Direction(1, 0),
                new Direction(0, 1),
                new Direction(-1, 0),
                new Direction(0, -1)
        );
    }
}
