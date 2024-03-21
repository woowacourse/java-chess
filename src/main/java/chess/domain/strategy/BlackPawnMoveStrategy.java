package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Row;
import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy{

    private static final List<Direction> DIRECTIONS = List.of(Direction.S, Direction.SE, Direction.SW);

    public BlackPawnMoveStrategy() {
        super(DIRECTIONS, Row.RANK7, Direction.S);
    }
}
