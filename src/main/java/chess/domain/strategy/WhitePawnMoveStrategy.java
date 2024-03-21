package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Row;
import java.util.List;

public class WhitePawnMoveStrategy extends PawnMoveStrategy{

    private static final List<Direction> DIRECTIONS = List.of(Direction.S, Direction.SE, Direction.SW);

    public WhitePawnMoveStrategy() {
        super(DIRECTIONS, Row.RANK2, Direction.N);
    }
}
