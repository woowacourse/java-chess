package chess.domain.moveStrategy;

import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;

public class BishopMove extends MultipleMove {
    @Override
    public List<Direction> directions() {
        return Arrays.asList(
                new Direction(1, 1),
                new Direction(1, -1),
                new Direction(-1, 1),
                new Direction(-1, -1)
        );
    }
}
