package chess.domain.strategy.pawn;

import chess.domain.position.Direction;
import chess.domain.position.Row;
import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy{

    private static final List<Direction> DIRECTIONS = List.of(Direction.S, Direction.SE, Direction.SW);

    public BlackPawnMoveStrategy() {
        super(DIRECTIONS, Row.RANK7, Direction.S);
    }
}
